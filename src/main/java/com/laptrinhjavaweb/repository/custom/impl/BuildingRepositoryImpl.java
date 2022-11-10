package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import com.laptrinhjavaweb.utils.ValidateUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
        try {
            String sql = buildQuery(buildingSearchBuilder);
            Query query = entityManager.createNativeQuery(sql, BuildingEntity.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private String buildQuery(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder queryFinal = new StringBuilder("select * from building AS bd ");
        StringBuilder join = new StringBuilder();
        buildJoinQuery(join, buildingSearchBuilder);
        StringBuilder where = new StringBuilder(SystemConstant.ONE_EQUAL_ONE);
        List<String> likeFields = Arrays.asList("name", "ward", "street", "managername", "managerphone");
        List<String> operatorFields = Arrays.asList("floorarea", "district", "numberOfBasement", "direction", "level");
        buildQueryPart1(buildingSearchBuilder, where, likeFields, operatorFields);
        buildQueryPart2(buildingSearchBuilder, where);
        where.append("\nGROUP BY bd.id");
        queryFinal
                .append(join)
                .append(where);
        System.out.println("===================================");
        System.out.println(queryFinal.toString());
        return queryFinal.toString();
    }


    private void buildQueryPart1(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where, List<String> likeFields, List<String> operatorFields) {
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object fieldValue = field.get(buildingSearchBuilder);
                if (ValidateUtil.isValid(fieldValue)) {
                    String fieldName = field.getName().toLowerCase();
                    for (String operatorField : operatorFields) {
                        if (operatorField.equals(fieldName)) {
                            if (field.getType().toString().equals("class java.lang.String")) {
                                where.append(String.format("\nand bd.%s = '%s'", fieldName, fieldValue.toString()));
                                break;
                            }
                            if (field.getType().toString().equals("class java.lang.Integer")) {
                                where.append(String.format("\nand bd.%s = %s", fieldName, fieldValue.toString()));
                                break;
                            }
                        }
                    }
                    for (String likeField : likeFields) {
                        if (likeField.equals(fieldName)) {
                            where.append(String.format("\nand bd.%s like '%s'", fieldName, "%" + fieldValue.toString() + "%"));
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buildQueryPart2(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
        if (buildingSearchBuilder.getRentTypes() != null && buildingSearchBuilder.getRentTypes().size() > 0) {
            where.append("\nand (");
            String renttypes = buildingSearchBuilder
                    .getRentTypes()
                    .stream()
                    .map(item -> ("bd.type like '%" + item + "%'"))
                    .collect(Collectors.joining(" or "));
            where.append(renttypes);
            where.append(" )");
        }

        if (ValidateUtil.isValid(buildingSearchBuilder.getStaffID())) {
            where.append("\nand u.id = " + buildingSearchBuilder.getStaffID());
        }

        if (ValidateUtil.isValid(buildingSearchBuilder.getRentAreaFrom())) {
            where.append("\nand EXISTS (select * from rentarea as ra where bd.id=ra.buildingid and ra.value >= "
                    + buildingSearchBuilder.getRentAreaFrom() + ")");
        }
        if (ValidateUtil.isValid(buildingSearchBuilder.getRentAreaTo())) {
            where.append("\nand EXISTS (select * from rentarea as ra where bd.id=ra.buildingid and ra.value <= "
                    + buildingSearchBuilder.getRentAreaTo() + ")");
        }

        if (ValidateUtil.isValid(buildingSearchBuilder.getRentPriceFrom())) {
            where.append("\nand bd.rentprice >= " + buildingSearchBuilder.getRentPriceFrom());
        }
        if (ValidateUtil.isValid(buildingSearchBuilder.getRentPriceTo())) {
            where.append("\nand bd.rentprice <= " + buildingSearchBuilder.getRentPriceTo());
        }

    }


    private void buildJoinQuery(StringBuilder join, BuildingSearchBuilder buildingSearchBuilder) {
        if (ValidateUtil.isValid(buildingSearchBuilder.getRentAreaTo()) || ValidateUtil.isValid(buildingSearchBuilder.getRentAreaFrom()))
            join.append("\nINNER JOIN rentarea AS ra on bd.id = ra.buildingid ");
        if (ValidateUtil.isValid(buildingSearchBuilder.getStaffID()))
            join.append( "\nINNER JOIN assignmentbuilding AS ab on bd.id = ab.buildingid " +
                           "INNER JOIN user AS u on ab.staffid = u.id ");
    }
}
