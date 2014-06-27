package com.cinsec.dmc.dao.impl;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.cinsec.dmc.dao.IGeneralDao;
import com.cinsec.dmc.dao.impl.Criterion.CompareCriterion;
import com.cinsec.dmc.dao.impl.Criterion.CompareType;
import com.cinsec.dmc.dao.impl.Criterion.CriterionType;
import com.cinsec.dmc.vo.GeneralVo;

@Repository
public class GeneralDao extends BaseDao<GeneralVo> implements IGeneralDao {

	private static final long serialVersionUID = 1L;

	@Override
	public long getResultSize() throws SQLException {
		String sql = " select count(*) from v_general_record";
		Query query = this.getEntityManager().createNativeQuery(sql);
		return Long.parseLong(query.getSingleResult().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GeneralVo> listResults(int from, int length)
			throws SQLException {
		String sql = " select * from v_general_record order by time desc";
		Query query = this.getEntityManager().createNativeQuery(sql);
		query.setFirstResult(from).setMaxResults(length);
		List<Object[]> result = query.getResultList();
		List<GeneralVo> list = new ArrayList<GeneralVo>();
		GeneralVo vo;
		for (Object[] obj : result) {
			vo = new GeneralVo();
			vo.setType((String) obj[0]);
			vo.setTime((Date) obj[1]);
			vo.setSourceIp((String) obj[2]);
			vo.setDestIp(((String) obj[3]));
			vo.setSourceMac(((String) obj[4]));
			vo.setDestMac(((String) obj[5]));
			vo.setUrl(((String) obj[6]));
			vo.setSender(((String) obj[7]));
			vo.setReceiver(((String) obj[8]));
			vo.setKeyword(((String) obj[9]));
			list.add(vo);

		}

		return list;
	}

	@Override
	public long getResultSize(String groupOp, List<Criterion> criteria)
			throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from v_general_record where 1=1 ");
		String field;
		String value;
		CriterionType criterionType;
		CompareType compareType;
		for (Criterion criterion : criteria) {
			field = criterion.getField();
			value = (String) criterion.getValue();
			if(StringUtils.isEmpty(value)){
				continue;
			}
			try {
				value =	new String(value.getBytes("ISO-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
			}
			criterionType = criterion.getCriterionType();
			switch (criterionType) {
			case LIKE:
				if(value.equals("%")){
					break;
				}
				if (field.equals("ip")) {
					sql.append(" and ( source_ip like '" + value
							+ "' or  dest_ip like '" + value + "' ) ");
					break;
				}
				if (field.equals("mac")) {
					sql.append(" and ( source_mac like '" + value
							+ "' or dest_mac like '" + value + "' ) ");
					break;
				}
				if (field.equals("email")) {
					sql.append(" and ( sender like '" + value
							+ "' or receiver like '" + value + "' ) ");
					break;
				}
				sql.append(" and  " + field + " like '" + value + "'");
				break;
			case COMPARE:
				compareType = ((CompareCriterion) criterion).getCompareType();
				switch (compareType) {
				case GTE:
					sql.append(" and " + field + ">= '" + value+"' ");
					break;
				case LTE:
					sql.append(" and " + field + "<= '" + value+"' ");
					break;
				default:
					break;
				}
			default:
				break;
			}
		}
		sql.append(" order by time desc ");
		Query query = this.getEntityManager().createNativeQuery(sql.toString());
		return Long.parseLong(query.getSingleResult().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GeneralVo> listResults(String groupOp,
			List<Criterion> criteria, int from, int length) throws SQLException{
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from v_general_record where 1=1 ");
		String field;
		String value;
		CriterionType criterionType;
		CompareType compareType;
		for (Criterion criterion : criteria) {
			field = criterion.getField();
			value = (String) criterion.getValue();
		
			if(StringUtils.isEmpty(value)){
				continue;
			}
			try {
				value =	new String(value.getBytes("ISO-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
			}
			criterionType = criterion.getCriterionType();
			switch (criterionType) {
			case LIKE:
				
				if(value.equals("%")){
					break;
				}
				
				if (field.equals("ip")) {
					sql.append(" and ( source_ip like '%" + value
							+ "%' or  dest_ip like '%" + value + "%' ) ");
					break;
				}
				if (field.equals("mac")) {
					sql.append(" and ( source_mac like '%" + value
							+ "%' or dest_mac like '%" + value + "%' ) ");
					break;
				}
				if (field.equals("email")) {
					sql.append(" and ( sender like '%" + value
							+ "%' or receiver like '%" + value + "%' ) ");
					break;
				}
				sql.append(" and  " + field + " like '%" + value + "%'");
				break;
			case COMPARE:
				compareType = ((CompareCriterion) criterion).getCompareType();
				switch (compareType) {
				case GTE:
					sql.append(" and " + field + ">= '" + value+"'");
					break;
				case LTE:
					sql.append(" and " + field + "<= '" + value+"'");
					break;
				default:
					break;
				}
			default:
				break;
			}
		}
		sql.append(" order by time desc ");
		Query query = this.getEntityManager().createNativeQuery(sql.toString());
		query.setFirstResult(from).setMaxResults(length);
		List<Object[]> result = query.getResultList();
		List<GeneralVo> list = new ArrayList<GeneralVo>();
		GeneralVo vo;
		for (Object[] obj : result) {
			vo = new GeneralVo();
			vo.setType((String) obj[0]);
			vo.setTime((Date) obj[1]);
			vo.setSourceIp((String) obj[2]);
			vo.setDestIp(((String) obj[3]));
			vo.setSourceMac(((String) obj[4]));
			vo.setDestMac(((String) obj[5]));
			vo.setUrl(((String) obj[6]));
			vo.setSender(((String) obj[7]));
			vo.setReceiver(((String) obj[8]));
			vo.setKeyword(((String) obj[9]));
			list.add(vo);

		}

		return list;
	}

}
