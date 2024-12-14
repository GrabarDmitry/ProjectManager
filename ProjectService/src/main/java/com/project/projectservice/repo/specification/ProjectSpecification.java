package com.project.projectservice.repo.specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.config.Task;

import com.project.projectservice.model.Project;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ProjectSpecification {
    public static Specification<Project> withFilters(String status, String managerId, Date dateEnd, Date dateStart, String title) {
        return (Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }

            if (managerId != null) {
                predicates.add(cb.equal(root.get("managerId"), managerId));
            }

            if (dateEnd != null) {
                predicates.add(cb.lessThan(root.get("dateEnd"), dateEnd));
            }

            if (dateStart != null) {
                predicates.add(cb.lessThan(root.get("dateStart"), dateStart));
            }

            if (title != null) {
                predicates.add(cb.like(root.get("title"), "%" + title + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
