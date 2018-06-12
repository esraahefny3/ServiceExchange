package com.service_exchange.api_services.dao.transaction;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionCustomImpl implements TransactionCustomInterface {

    ////////////////////////////Esraa////////////////////////////
    @PersistenceContext
    EntityManager entityManager;

    private int limit = 20;
    private String tableName1 = "transaction_info", tableName2 = "service", tableName3 = "message";

    @Override
    public List<Integer> getAllUserTransactions(Integer userId, Integer pageNum) {
        Query query = entityManager.createNativeQuery("select  m.transaction_id,max(m.date) " +
                "from " + tableName1 + " t " +
                "inner join " + tableName3 + " m " +
                "on t.started_by=? " +
                "inner join " + tableName2 + " s " +
                "on  s.made_by=? " +
                "where t.started_by=? or  s.made_by=?  group by  m.transaction_id order by max(m.date) desc ");
        query.setParameter(1, userId.intValue());
        query.setParameter(2, userId.intValue());
        query.setParameter(3, userId.intValue());
        query.setParameter(4, userId.intValue());
//select m.transaction_id,max(m.date) from transaction_info t inner join message m inner join service s where t.started_by=1 or  s.made_by=1 group by m.transaction_id order by max(m.date) desc
//        //Paginering
        query.setFirstResult(pageNum * limit);
        query.setMaxResults(limit);

        List<Object[]> rows = query.getResultList();
        List<Integer> resultList = new ArrayList<>(rows.size());
        for (Object[] row : rows) {
            resultList.add(new Integer((Integer) row[0]));
        }
        if (resultList.size() > 0) {
            return resultList;
        } else {
            return null;
        }
    }
    ////////////////////////////Esraa////////////////////////////
}
