package com.likelion.fillyouinback.member.filter;

import com.likelion.fillyouinback.member.domain.Member;
import com.likelion.fillyouinback.memberSkill.domain.MemberSkill;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class MemberSpecification {
  public static Specification<Member> hasName(String name) {
    return (root, query, criteriaBuilder) -> {
      Expression<String> fullName =
              criteriaBuilder.concat(
                      criteriaBuilder.concat(root.get("lastName"), " "), root.get("firstName"));
      return criteriaBuilder.like(fullName, "%" + name + "%");
    };
  }

  public static Specification<Member> hasDepartment(String department) {
    return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("department"), department);
  }

  public static Specification<Member> hasSemester(Integer semester) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("semester"), semester);
  }

  public static Specification<Member> hasSkill(String skill) {
    return (root, query, criteriaBuilder) -> {
      Join<Member, MemberSkill> skillsJoin = root.join("skills");
      return criteriaBuilder.equal(skillsJoin.get("name"), skill);
    };
  }

  public static Specification<Member> hasJob(String job) {
    return (root, query, criteriaBuilder) -> {
      Join<Member, MemberSkill> jobsJoin = root.join("jobs");
      return criteriaBuilder.equal(jobsJoin.get("name"), job);
    };
  }

  public static Specification<Member> hasField(String field) {
    return (root, query, criteriaBuilder) -> {
      Join<Member, MemberSkill> fieldsJoin = root.join("fields");
      return criteriaBuilder.equal(fieldsJoin.get("name"), field);
    };
  }

  public static Specification<Member> notMe(Long memberId) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.notEqual(root.get("id"), memberId);
  }
}