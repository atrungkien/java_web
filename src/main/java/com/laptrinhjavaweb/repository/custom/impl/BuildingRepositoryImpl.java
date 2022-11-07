package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.SearchEntity.BuildingSearchEntity;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import com.laptrinhjavaweb.utils.CheckUtil;
import com.laptrinhjavaweb.utils.ValidateUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> findAll(BuildingSearchEntity buildingSearchEntity) {
        try {
            String sql = Query(buildingSearchEntity);
            Query query = entityManager.createNativeQuery(sql, BuildingEntity.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private String Query(BuildingSearchEntity buildingSearchEntity) {
        StringBuilder queryFinal = new StringBuilder("select * from building AS bd ");
        StringBuilder join = new StringBuilder();
        buildJoinQuery(join, buildingSearchEntity);
        StringBuilder where = new StringBuilder(SystemConstant.ONE_EQUAL_ONE);
        buildQuery(buildingSearchEntity, where);
        where.append(" GROUP BY bd.id");
        queryFinal.append(join).append(where);
        System.out.println("===================================");
        System.out.println(queryFinal.toString());
        return queryFinal.toString();

    }
    private void buildQuery(BuildingSearchEntity buildingSearchEntity, StringBuilder where) {
        if (buildingSearchEntity.getRentTypes() != null && buildingSearchEntity.getRentTypes().size() > 0) {
            where.append(" and (");
            String renttypes = buildingSearchEntity.getRentTypes().stream().map(item -> (" bd.type like '%" + item + "%'")).collect(Collectors.joining(" or "));
            where.append(renttypes);
            where.append(" )");
        }


        if (ValidateUtil.isValid(buildingSearchEntity.getName())) {
            where.append(" AND b.name like '%" + buildingSearchEntity.getName() + "%' ");
        }
        if (ValidateUtil.isValid(buildingSearchEntity.getFloorArea())) {
            where.append(" AND b.floorarea = " + buildingSearchEntity.getFloorArea() + "");
        }
        if (ValidateUtil.isValid(buildingSearchEntity.getNumberOfBasement())) {
            where.append(" AND b.numberofbasement = " + buildingSearchEntity.getNumberOfBasement() + "");
        }
        if (ValidateUtil.isValid(buildingSearchEntity.getStreet())) {
            where.append(" AND b.name like '%" + buildingSearchEntity.getStreet() + "%' ");
        }
        if (ValidateUtil.isValid(buildingSearchEntity.getWard())) {
            where.append(" AND b.name like '%" + buildingSearchEntity.getWard() + "%' ");
        }
        if (ValidateUtil.isValid(buildingSearchEntity.getManagerName())) {
            where.append(" AND b.name like '%" + buildingSearchEntity.getManagerName() + "%' ");
        }
        if (ValidateUtil.isValid(buildingSearchEntity.getManagerPhone())) {
            where.append(" AND b.name like '%" + buildingSearchEntity.getManagerPhone() + "%' ");
        }


        if (ValidateUtil.isValid(buildingSearchEntity.getStaffID())) {
            where.append(" and u.id = " + buildingSearchEntity.getStaffID());
        }

        if (ValidateUtil.isValid(buildingSearchEntity.getRentAreaFrom())) {
            where.append(" and EXISTS (select * from rentarea as ra where bd.id=ra.buildingid and ra.value >= "
                    + buildingSearchEntity.getRentAreaFrom() + ")");
        }
        if (ValidateUtil.isValid(buildingSearchEntity.getRentAreaTo())) {
            where.append(" and EXISTS (select * from rentarea as ra where bd.id=ra.buildingid and ra.value <= "
                    + buildingSearchEntity.getRentAreaTo() + ")");
        }

        if (ValidateUtil.isValid(buildingSearchEntity.getRentPriceFrom())) {
            where.append(" and bd.rentprice >= " + buildingSearchEntity.getRentPriceFrom());
        }
        if (ValidateUtil.isValid(buildingSearchEntity.getRentPriceTo())) {
            where.append(" and bd.rentprice <= " + buildingSearchEntity.getRentPriceTo());
        }

    }


    private void buildJoinQuery(StringBuilder join, BuildingSearchEntity buildingSearchEntity) {
        if (ValidateUtil.isValid(buildingSearchEntity.getRentAreaTo()) || ValidateUtil.isValid(buildingSearchEntity.getRentAreaFrom()))
            join.append(" INNER JOIN rentarea AS ra on bd.id = ra.buildingid ");
        if (ValidateUtil.isValid(buildingSearchEntity.getStaffID()))
            join.append( " INNER JOIN assignmentbuilding AS ab on bd.id = ab.buildingid " +
                    "INNER JOIN user AS u on ab.staffid = u.id ");
    }
}
