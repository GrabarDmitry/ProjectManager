package com.project.taskservice.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import com.project.taskservice.model.Task;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class TaskSpecification {
    public static Specification<Task> withFilters(String status, String userId, Long projectId, String dateEnd, String title) {
        return (Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }

            if (userId != null) {
                predicates.add(cb.equal(root.get("userId"), userId));
            }

            if (projectId != null) {
                predicates.add(cb.equal(root.get("projectId"), projectId));
            }

            if (dateEnd != null) {
                predicates.add(cb.lessThan(root.get("dateEnd"), dateEnd));
            }

            if (title != null) {
                predicates.add(cb.like(root.get("title"), "%" + title + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
