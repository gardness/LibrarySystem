package com.ascending.training.service;

import com.ascending.training.model.IssueStatus;
import com.ascending.training.repository.IssueStatusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueStatusService {

    @Autowired
    private IssueStatusDao issueStatusDao;

    long save(IssueStatus issueStatus) {
        return issueStatusDao.save(issueStatus);
    }

    boolean update(IssueStatus issueStatus) {
        return issueStatusDao.update(issueStatus);
    }

    boolean delete(long issueStatusesId) {
        return issueStatusDao.delete(issueStatusesId);
    }

    List<IssueStatus> getIssueStatuses() {
        return issueStatusDao.getIssueStatuses();
    }

    IssueStatus getIssueStatusById(long id) {
        return issueStatusDao.getIssueStatusById(id);
    }
}
