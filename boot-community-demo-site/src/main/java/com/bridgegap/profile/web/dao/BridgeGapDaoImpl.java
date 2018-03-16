package com.bridgegap.profile.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.ejb.QueryHints;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.bridgegap.profile.domain.BridgeGapState;

@Service("bridgeGapDao")
public class BridgeGapDaoImpl implements BridgeGapDao {
	@PersistenceContext(unitName = "blPU")
    protected EntityManager em;

	@Override
	public List<BridgeGapState> populateBGStates() {
		Query query = em.createNamedQuery("BG_FIND_STATES");
		query.setHint(QueryHints.HINT_CACHEABLE, true);
		return query.getResultList();
	}

}
