package com.questionnaire.dao.impl;

import org.apache.commons.lang3.time.DateUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Criterion {
    private CriterionType criterionType;
    private String tableName;
    private String field;
    private Object value;

    // 将Criteria转换为SQL条件语句
    public static String convertToSql(String groupOp, List<Criterion> criteria) {
        String criteriaString = "";
        StringBuilder sb = new StringBuilder();
        String field;
        String[] strs;
        for (Criterion criterion : criteria) {
            field = criterion.getField();
            strs = field.split("\\.");
            if (strs.length > 1) {
                field = strs[0] + ".id";
            }
            String prefix = criterion.getFieldPrefix();
            switch (criterion.getCriterionType()) {
                case EQUAL:
                    sb.append(prefix + field + "=? " + groupOp + " ");
                    break;
                case LIKE:
                    sb.append(prefix + field + " like ? " + groupOp + " ");
                    break;

                case NOT_LIKE:
                    sb.append(prefix + field + " not like ? " + groupOp + " ");
                    break;
                case COMPARE:
                    CompareType compareType = ((CompareCriterion) criterion)
                            .getCompareType();
                    switch (compareType) {
                        case EQ:
                            sb.append(prefix + field + "=? " + groupOp + " ");
                            break;
                        case NE:
                            sb.append(prefix + field + "<>? " + groupOp + " ");
                            break;
                        case GT:
                            sb.append(prefix + field + ">? " + groupOp + " ");
                            break;
                        case GTE:
                            sb.append(prefix + field + ">=? " + groupOp + " ");
                            break;
                        case LT:
                            sb.append(prefix + field + "<? " + groupOp + " ");
                            break;
                        case LTE:
                            sb.append(prefix + field + "<=? " + groupOp + " ");
                            break;
                    }
                    break;
            }
        }
        int i = -1;
        if ((i = sb.lastIndexOf(" " + groupOp + " ")) != -1) {
            criteriaString = sb.substring(0, i);
        }
        return criteriaString;
    }

    // 将Criteria各条件的值转换为List<Object>
    public static List<Object> getCriteriaValues(Class<?> clazz,
                                                 List<Criterion> criteria) throws Exception {
        List<Object> criteriaValues = criteria.isEmpty() ? Collections
                .emptyList() : new ArrayList<Object>();
        Class<?> type;
        Object value;
        String field;
        String[] strs;
        for (Criterion criterion : criteria) {
            field = criterion.getField();
            value = criterion.getValue();
            strs = field.split("\\.");
            if (strs.length > 1) {
                value = Integer.parseInt((String) criterion.getValue());
            }
            type = clazz.getDeclaredField(strs[0]).getType();
            if (Integer.class.equals(type)) {
                value = Integer.valueOf(String.valueOf(value));
            } else if (Long.class.equals(type)) {
                value = Long.valueOf(String.valueOf(value));
            } else if (Double.class.equals(type)) {
                value = Double.valueOf(String.valueOf(value));
            } else if (Float.class.equals(type)) {
                value = Float.valueOf(String.valueOf(value));
            } else if (Date.class.equals(type)) {
                value = DateUtils.parseDate(String.valueOf(value),
                        "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
            }
            criteriaValues.add(value);
        }
        return criteriaValues;
    }

    public static Criterion getCompareCriterion(CompareType compareType,
                                                String field, Object value, String tableName) {
        CompareCriterion compareCriterion = new CompareCriterion();
        compareCriterion.setCriterionType(CriterionType.COMPARE);
        compareCriterion.setCompareType(compareType);
        compareCriterion.setField(field);
        compareCriterion.setValue(value);
        compareCriterion.setTableName(tableName);
        return compareCriterion;
    }

    public static Criterion getLikeCriterion(String field, Object value,
                                             String tableName) {
        LikeCriterion likeCriterion = new LikeCriterion();
        likeCriterion.setCriterionType(CriterionType.LIKE);
        likeCriterion.setField(field);
        likeCriterion.setValue(value);
        likeCriterion.setTableName(tableName);
        return likeCriterion;
    }

    public static Criterion getNotLikeCriterion(String field, Object value,
                                                String tableName) {
        NotLikeCriterion notLikeCriterion = new NotLikeCriterion();
        notLikeCriterion.setCriterionType(CriterionType.NOT_LIKE);
        notLikeCriterion.setField(field);
        notLikeCriterion.setValue(value);
        notLikeCriterion.setTableName(tableName);
        return notLikeCriterion;
    }

    public static Criterion getEqualCriterion(String field, Object value,
                                              String tableName) {
        EqualCriterion equalCriterion = new EqualCriterion();
        equalCriterion.setCriterionType(CriterionType.EQUAL);
        equalCriterion.setField(field);
        equalCriterion.setValue(value);
        equalCriterion.setTableName(tableName);
        return equalCriterion;
    }

    public CriterionType getCriterionType() {
        return criterionType;
    }

    public void setCriterionType(CriterionType criterionType) {
        this.criterionType = criterionType;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFieldPrefix() {
        return (tableName == null || tableName.length() == 0) ? "" : tableName
                + ".";
    }

    public static enum CriterionType {
        EQUAL, LIKE, COMPARE, NOT_LIKE
    }

    public static enum CompareType {
        GT, GTE, LT, LTE, EQ, NE
    }

    public static class LikeCriterion extends Criterion {
    }

    public static class NotLikeCriterion extends Criterion {
    }

    public static class EqualCriterion extends Criterion {
    }

    public static class CompareCriterion extends Criterion {
        private CompareType compareType;

        public CompareType getCompareType() {
            return compareType;
        }

        public void setCompareType(CompareType compareType) {
            this.compareType = compareType;
        }
    }
}