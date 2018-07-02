package com.dayLeasing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayLeasing.dao.DashboardDao;
import com.dayLeasing.dao.model.Testtable;

// TODO: Auto-generated Javadoc
/**
 * The Class DashboardSeviceImpl.
 *
 * @author Balaram
 */
@Service("DashboardService")
public class DashboardSeviceImpl implements DashboardService {

  /** The dao. */
  @Autowired
  DashboardDao dao;

  /* (non-Javadoc)
   * @see com.dayLeasing.service.DashboardService#getName(int)
   */
  @Override
  public List<Testtable> getName(int index) {
    // TODO Auto-generated method stub
    List<Testtable> names = dao.getName(index);

    return names;

  }

}
