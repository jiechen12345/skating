package com.oppo.business.impl;

import com.oppo.Entity.PreOrder;
import com.oppo.business.VerfyService;
import com.oppo.dao.*;
import com.oppo.dto.PreorderDto;
import com.oppo.dto.PreorderPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toList;

/**
 * Created by JieChen on 2018/10/3.
 */
@Service
public class VerifyServiceImpl implements VerfyService {
    @Autowired
    private PreorderDao preorderDao;


//    @Override
//    public List<PreorderDto> findAll() {
//        List<PreorderDto> preorderDtoList = preorderDao.findAll(PageRequest.of(0, 5)).stream()
//                .map(this::getBookDto)
//                .collect(Collectors.toList());
//        return preorderDtoList;
//    }

    @Override
    public PreorderPage getAllForm(Integer page, Integer PageSize) {
        //todo: 只顯示OTP過的人?
        Page<PreOrder> p = preorderDao.findAll((root, query, cb) -> {
            query.orderBy(cb.desc(root.get("otpPassTime")),cb.desc(root.get("createTime")));
            return cb.and();
        }, PageRequest.of(page - 1, PageSize));

            PreorderPage result = new PreorderPage();
            result.setContents(p.getContent()
                    .stream()

                    .map(it -> new PreorderDto(
                            it.getId(),
                            (it.getSessions() != null) ? it.getSessions().getSessionsName() : "",
                            (it.getSessions() != null) ? it.getSessions().getStartTime()+"-"+it.getSessions().getEndTime() : "",
                            it.getPreorderDate(),
                            it.getGroupName(),
                            it.getApplicantName(),
                            it.getApplicantPhone(),
                            it.getApplicantEmail(),
                            it.getGroupNum(),
                            it.getStatus().getStatusName()
                        )
                )
                .collect(toList()));
            result.setCurrentPage(page);
            result.setTotalPages(p.getTotalPages() > 0 ? p.getTotalPages() : 1);
            result.setCount(p.getTotalElements());
            return result;
        }


//    @Override
//    public PreorderPage queryAll(Integer page, Integer pageSize, BookReq preOrderReq) {
//        Page<PreOrder> p = preorderDao.findAll((root, query, cb) -> {
//            query.orderBy(cb.desc(root.get("id")));
//
//            List<Predicate> predicates = new LinkedList<>();
//
//            Optional.ofNullable(preOrderReq.getQ_id()).filter(it -> !it.isEmpty()).ifPresent(q_id -> {
//                predicates.add(cb.greaterThanOrEqualTo(root.get("id"), Long.parseLong(q_id)));
//            });
//            Optional.ofNullable(preOrderReq.getQ_id2()).filter(it -> !it.isEmpty()).ifPresent(q_id2 -> {
//                predicates.add(cb.lessThanOrEqualTo(root.get("id"), Long.parseLong(q_id2)));
//            });
//            Optional.ofNullable(preOrderReq.getQ_amt()).ifPresent(q_amt -> {
//                predicates.add(cb.ge(root.get("amt"), q_amt));
//            });
//            Optional.ofNullable(preOrderReq.getQ_amt2()).ifPresent(q_amt2 -> {
//                predicates.add(cb.le(root.get("amt"), q_amt2));
//            });
//            Optional.ofNullable(preOrderReq.getQ_invYM()).filter(it -> !it.isEmpty()).ifPresent(q_invYm -> {
//                predicates.add(cb.greaterThanOrEqualTo(root.get("invYM"), (q_invYm)));
//            });
//            Optional.ofNullable(preOrderReq.getQ_invYM2()).filter(it -> !it.isEmpty()).ifPresent(q_invYm2 -> {
//                predicates.add(cb.lessThanOrEqualTo(root.get("invYM"), (q_invYm2)));
//            });
//            Optional.ofNullable(preOrderReq.getQ_paidDat()).filter(it -> !it.isEmpty()).ifPresent(q_paidDat -> {
//                predicates.add(cb.greaterThanOrEqualTo(root.get("paidDat"), new DateTime(q_paidDat).withTimeAtStartOfDay().toDate()));
//            });
//            Optional.ofNullable(preOrderReq.getQ_paidDat2()).filter(it -> !it.isEmpty()).ifPresent(q_paidDat2 -> {
//                predicates.add(cb.lessThanOrEqualTo(root.get("paidDat"), new DateTime(q_paidDat2).withTimeAtStartOfDay().plusDays(1).minusMillis(1).toDate()));
//            });
//            Optional.ofNullable(preOrderReq.getQ_incomeOrExpend()).filter(it -> !it.isEmpty()).ifPresent(q_incomeOrExpend -> {
//                predicates.add(cb.equal(root.get("incomeOrExpend"), q_incomeOrExpend));
//            });
//            Optional.ofNullable(preOrderReq.getQ_invNo()).filter(it -> !it.isEmpty()).ifPresent(q_invNo -> {
//                predicates.add(cb.like(root.get("invNo"), "%" + q_invNo + "%"));
//            });
//
//            Optional.ofNullable(preOrderReq.getQ_customerId()).filter(it -> it!=0).ifPresent(q_customerId -> {
//                predicates.add(cb.equal(root.get("project").get("customer").get("id"), q_customerId));
//            });
//
//            Optional.ofNullable(preOrderReq.getQ_projectId()).ifPresent(q_projectId -> {
//                predicates.add(cb.equal(root.get("project").get("id"), q_projectId));
//            });
//
////            if (!"".equals(preOrderReq.getQ_invNo())){
////                Predicate invNo = cb.like(root.get("invNo"), '%'+preOrderReq.getQ_invNo()+'%');
////                predicates.add(invNo);
////            }
//
//            Optional.ofNullable(preOrderReq.getQ_invoice()).ifPresent(q_invoice -> {
//                predicates.add(cb.equal(root.get("invoice"), q_invoice));
//            });
//            Optional.ofNullable(preOrderReq.getQ_paid()).ifPresent(q_paid -> {
//                predicates.add(cb.equal(root.get("paid"), q_paid));
//            });
//            Optional.ofNullable(preOrderReq.getQ_description()).ifPresent(q_description -> {
//                predicates.add(cb.like(root.get("description"), '%' + q_description + '%'));
//            });
//
//            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
//        }, PageRequest.of(page - 1, pageSize));
//
//        PreorderPage result = new PreorderPage();
//        result.setContents(p.getContent()
//                .stream()
//                .map(it -> new PreorderDto(
//                                it.getId(),
//                                it.getIncomeOrExpend(),
//                                it.getInvoice(),
//                                it.getInvYM(),
//                                it.getInvNo(),
//                                it.getPaid(),
//                                it.getPaidDat(),
//                                it.getAmt(),
//                                it.getProject().getCustomer().getId(),
//                                it.getProject().getCustomer().getCustNm(),
//                                it.getProject().getId(),
//                                it.getProject().getProjectName(),
//                                it.getCreateDat(),
//                                it.getUpdateDat(),
//                                (it.getCreateMember() != null) ? it.getCreateMember().getId() : 0,
//                                (it.getCreateMember() != null) ? it.getCreateMember().getName() : "",
//                                (it.getUpdateMember() != null) ? it.getUpdateMember().getId() : 0,
//                                (it.getUpdateMember() != null) ? it.getUpdateMember().getName() : "",
//                                it.getDescription(),
//                                it.getRemarks()
//                        )
//                )
//                .collect(toList()));
//        result.setCurrentPage(page);
//        result.setTotalPages(p.getTotalPages() > 0 ? p.getTotalPages() : 1);
//        result.setCount(p.getTotalElements());
//        return result;
//    }
//
//    @Override
//    public PreorderDto queryOne(String bookId) {
//        PreOrder preOrder = preorderDao.findById(bookId).get();
//        PreorderDto preorderDto = getBookDto(preOrder);
//        return preorderDto;
//    }

//        private PreorderDto getBookDto (PreOrder preOrder){
//            PreorderDto preorderDto = new PreorderDto();
//            preorderDto.setId(preOrder.getId());
//            preorderDto.setIncomeOrExpend(preOrder.getIncomeOrExpend());
//            preorderDto.setInvoice(preOrder.getInvoice());
//            preorderDto.setInvYM(preOrder.getInvYM());
//            preorderDto.setInvNo(preOrder.getInvNo());
//            preorderDto.setPaid(preOrder.getPaid());
//            preorderDto.setPaidDat(preOrder.getPaidDat());
//            preorderDto.setAmt(preOrder.getAmt());
//            preorderDto.setDescription(preOrder.getDescription());
//            preorderDto.setRemarks(preOrder.getRemarks());
//            preorderDto.setCreateDat(preOrder.getCreateDat());
//            preorderDto.setUpdateDat(preOrder.getUpdateDat());
//            if (preOrder.getCreateMember() != null) {
//                preorderDto.setCreateMemberId(preOrder.getCreateMember().getId());
//                preorderDto.setCreateMemberName(preOrder.getCreateMember().getName());
//            }
//            if (preOrder.getUpdateMember() != null) {
//                preorderDto.setUpdateMemberId(preOrder.getUpdateMember().getId());
//                preorderDto.setUpdateMemberName(preOrder.getUpdateMember().getName());
//            }
//            if (preOrder.getProject() != null) {
//                preorderDto.setProjectId(preOrder.getProject().getId());
//                preorderDto.setProjectName(preOrder.getProject().getProjectName());
//                preorderDto.setCustomerId(preOrder.getProject().getCustomer().getId());
//                preorderDto.setCustomerNm(preOrder.getProject().getCustomer().getCustNm());
//            } else {
//                preorderDto.setProjectName("");
//            }
//            return preorderDto;
//        }

//    private PreOrder getSetupBook(PreOrder preOrder, BookReq preOrderReq) {
//        //PreOrder preOrder = new PreOrder();
//        System.out.println(preOrderReq.toString());
//        if ("create".equals(preOrderReq.getStatus())) {
//            preOrder.setId(preOrderReq.getId());
//            preOrder.setCreateDat(preOrderReq.getCreateDat());
//            if (preOrderReq.getCreateMemberId() != null) {
//                Member member = memberDao.findById(preOrderReq.getCreateMemberId()).get();
//                preOrder.setCreateMember(member);
//            }
//        }
//        if ("update".equals(preOrderReq.getStatus())) {
//            if (preOrderReq.getUpdateMemberId() != null) {
//                Member member = memberDao.findById(preOrderReq.getUpdateMemberId()).get();
//                preOrder.setUpdateMember(member);
//            }
//            preOrder.setUpdateDat(preOrderReq.getUpdateDat());
//        }
//        preOrder.setIncomeOrExpend(preOrderReq.getIncomeOrExpend());
//        preOrder.setInvoice((preOrderReq.getInvoice() == 1) ? true : false);
//        preOrder.setInvYM(Common.get(preOrderReq.getInvYM()));
//        preOrder.setInvNo(Common.get(preOrderReq.getInvNo()));
//        preOrder.setPaid((preOrderReq.getPaid() == 1) ? true : false);
//        preOrder.setPaidDat(preOrderReq.getPaidDat());
//        preOrder.setAmt(Common.get(preOrderReq.getAmt()));
//        preOrder.setDescription(Common.get(preOrderReq.getDescription()));
//        preOrder.setRemarks(Common.get(preOrderReq.getRemarks()));
//        if (preOrderReq.getProjectId() != null) {
//            Project project = projectDao.findById((preOrderReq.getProjectId())).get();
//            preOrder.setProject(project);
//        }
//        return preOrder;
//    }


    }
