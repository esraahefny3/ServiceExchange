package com.service_exchange.api_services.bussinesslayer.transactionbussiness;


import com.service_exchange.api_services.bussinessdaodelegates.transaction.TransactionDelegateInterface;
import com.service_exchange.api_services.bussinessdaodelegates.user.UserDelegateInterface;
import com.service_exchange.api_services.bussinesslayer.messagebussiness.MessageServiceInterface;
import com.service_exchange.api_services.dao.service.ServiceData;
import com.service_exchange.api_services.dao.transaction.TransactionDaoInterface;
import com.service_exchange.api_services.dao.transaction.TransactionDto;
import com.service_exchange.api_services.dao.user.UserDataInterFace;
import com.service_exchange.api_services.factories.AppFactory;
import com.service_exchange.entities.Message;
import com.service_exchange.entities.Service;
import com.service_exchange.entities.TransactionInfo;
import com.service_exchange.entities.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Component
public class TransactionService implements TransactionServiceInterface {


    @Autowired
    private TransactionDelegateInterface transactionDelegateInterfaceImpl;

    @Autowired
    private UserDelegateInterface userDelegateInterfaceImpl;

    @Autowired
    TransactionDelegateInterface delegate;

    @Autowired
    TransactionDelegateInterface transactionDelegate;

    @Autowired
    TransactionDaoInterface transactionDao;

    @Autowired
    UserDataInterFace userDataInterFace;

    @Autowired
    ServiceData serviceData;

    @Autowired
    private  MessageServiceInterface messageServiceInterfaceImpl;

    ////////////////////////////Esraa////////////////////////////

    @Override
    public TransactionDto userAcceptedThenApproveTransaction(TransactionDto transactionDto) {
        //security will check that startedby user is the service maker user
        TransactionInfo transactionInfo = transactionDelegateInterfaceImpl.checkIfTransactionExist(transactionDto.getId());
        //  UserTable serviceOfferedOrRequestedByUser=userDelegateInterfaceImpl.getUserById(transactionDto.getServiceOfferedOrRequestedByUserId());
        if (transactionInfo != null) {
            Service service = transactionInfo.getServiceId();
            if (transactionDelegateInterfaceImpl.getAllUserAcceptedTransactionsOnService(service).isEmpty() == true)
            //user can accept only one transaction
            {
                if (transactionInfo.getStartedBy() != null &&
                        (transactionInfo.getState().equals(TransactionInfo.PENDING_STATE) == true ||
                                transactionInfo.getState().equals(TransactionInfo.POSTPONED) == true)) {

                    UserTable transactionStartedByUser = transactionInfo.getStartedBy();
                    UserTable serviceBuyer;
                    UserTable serviceProvider;
                    if (transactionInfo.getType().equals(Service.OFFERED)) {
                        serviceBuyer = transactionInfo.getStartedBy();
                        serviceProvider = transactionInfo.getServiceId().getMadeBy();
                    } else {
                        serviceBuyer = transactionInfo.getServiceId().getMadeBy();
                        serviceProvider = transactionInfo.getStartedBy();
                    }

                    System.out.println("old balance buyer: " + serviceBuyer.getBalance());
                    System.out.println("old balance provider: " + serviceProvider.getBalance());
                    System.out.println("service price: " + transactionDto.getPrice());

                    if (serviceBuyer.getBalance() >= transactionDto.getPrice()) {

                        //make sure an l user l start l transaction hoa hoa l user l m2dm l service aw 3mlha request
                        // transactionInfo.setState(TransactionInfo.ACCEPTED_STATE);
                        transactionInfo.setPrice(transactionDto.getPrice());
                        transactionInfo.setStartedBy(transactionStartedByUser);
                        transactionInfo.setDuration(transactionDto.getDuration());
                        transactionInfo.setState(TransactionInfo.ON_PROGRESS_STATE);
                        transactionInfo.setStartDate(new Date());

                        serviceBuyer.setBalance(serviceBuyer.getBalance() - transactionDto.getPrice());

                        System.out.println("new balance buyer: " + serviceBuyer.getBalance());
                        System.out.println("new balance provider: " + serviceProvider.getBalance());
                        System.out.println("service price: " + transactionDto.getPrice());

                        userDataInterFace.save(serviceBuyer);

                        if (transactionDelegateInterfaceImpl.postponeAllOtherUserPindingTransactionOnService(service) >= 0) {
                            TransactionDto transactionDtoNew= transactionDelegateInterfaceImpl.saveTransaction(transactionInfo);

                            //start message
                                Optional<UserTable>userTableOptional=userDataInterFace.findById(service.getMadeBy().getId());
                            System.out.println("sjd"+service.getMadeBy().getId());
                                if(userTableOptional.isPresent())
                                {System.out.println(userTableOptional.get().getId());
                                    UserTable sender=userTableOptional.get();
                                    String messageText = sender.getName()+ " is waving you...";
                                    Message message=AppFactory.getMessageInstance();
                                    message.setText(messageText);
                                    messageServiceInterfaceImpl.sendTransactionMessage(sender.getId(),transactionDtoNew.getsByUser(),message,transactionDtoNew.getId());
                                    return transactionDtoNew;
                                }

                        }
                    }
                }
            }
        }
        return null;
    }


    @Override
    public TransactionDto userAcceptTransaction(TransactionDto transactionDto) {//service maker or requester can only accept
        //security will check that startedby user is the service maker user
        TransactionInfo transactionInfo = transactionDelegateInterfaceImpl.checkIfTransactionExist(transactionDto.getId());
        //  UserTable serviceOfferedOrRequestedByUser=userDelegateInterfaceImpl.getUserById(transactionDto.getServiceOfferedOrRequestedByUserId());
        if (transactionInfo != null) {
            Service service = transactionInfo.getServiceId();
            UserTable transactionStartedByUser = service.getMadeBy();
            if (transactionDelegateInterfaceImpl.getAllUserAcceptedTransactionsOnService(service).isEmpty() == true)
            //user can accept only one transaction
            {
                if (transactionStartedByUser != null && (transactionInfo.getState().equals(TransactionInfo.PENDING_STATE) == true || transactionInfo.getState().equals(TransactionInfo.POSTPONED) == true)) {
                    //make sure an l user l start l transaction hoa hoa l user l m2dm l service aw 3mlha request
                    transactionInfo.setState(TransactionInfo.ACCEPTED_STATE);
                    transactionInfo.setPrice(transactionDto.getPrice());
                    transactionInfo.setStartedBy(transactionStartedByUser);
                    transactionDto.setDuration(transactionDto.getDuration());
                    return transactionDelegateInterfaceImpl.saveTransaction(transactionInfo);
                }
            }
        }
        return null;
    }

    @Override
    public TransactionDto userApproveAcceptedTransaction(TransactionDto transactionDto) {
        //security will check that the user called this method is the started by user
        TransactionInfo transactionInfo = transactionDelegateInterfaceImpl.checkIfTransactionExist(transactionDto.getId());
        if (transactionInfo != null) {
            UserTable transactionStartedByUser = transactionInfo.getStartedBy();
            Service service = transactionInfo.getServiceId();
            if (transactionStartedByUser != null && service != null && transactionInfo.getState().equals(TransactionInfo.ACCEPTED_STATE) == true) {
                transactionInfo.setState(TransactionInfo.ON_PROGRESS_STATE);
                transactionInfo.setStartDate(new Date());
                if (transactionDelegateInterfaceImpl.postponeAllOtherUserPindingTransactionOnService(service) >= 0) {
                    return transactionDelegateInterfaceImpl.saveTransaction(transactionInfo);
                }
            }
        }
        return null;
    }

    @Override
    public boolean userRejectTransaction(TransactionDto transactionDto) {
        TransactionInfo transactionInfo = transactionDelegateInterfaceImpl.checkIfTransactionExist(transactionDto.getId());
        if (transactionInfo != null) {
            UserTable transactionStartedByUser = transactionInfo.getStartedBy();
            Service service = transactionInfo.getServiceId();
            if (transactionInfo != null && transactionStartedByUser != null && service != null && transactionInfo.getState().equals(TransactionInfo.ACCEPTED_STATE) == true) {

                int retVal = transactionDelegateInterfaceImpl.rejectAcceptedTransactionOnService(service);
                if (retVal > -1) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<TransactionDto> getAllUserTransactions(Integer userId, Integer pageNum) {
        UserTable user = userDelegateInterfaceImpl.getUserById(userId);
        if (user != null) {
            return transactionDelegateInterfaceImpl.getAllUserTransactions(user, pageNum);
        } else {
            return null;
        }


    }

    ////////////////////////////Esraa////////////////////////////

    ////////////////////////////Nouran////////////////////////////

    @Override
    public TransactionDto makeTransactionOnService(TransactionDto transactionDto) {
        if ((delegate.checkIfUserExists(transactionDto.getsByUser()) != null) &&
                (delegate.checkIfServiceExists(transactionDto.getServiceId()) != null)) {
            if (serviceData.findById(transactionDto.getServiceId()).isPresent()) {
                Service service = serviceData.findById(transactionDto.getServiceId()).get();
                UserTable serviceBuyer;
                if (service.getType().equals(Service.OFFERED)) {
                    serviceBuyer = userDataInterFace.findById(transactionDto.getsByUser()).get();
                } else {
                    serviceBuyer = service.getMadeBy();
                }
                if (serviceBuyer.getBalance() >= transactionDto.getPrice()) {
                    Boolean hasOnProgressTransactions = false;
                    List<TransactionInfo> onProgressTransactionsOnService = transactionDao.findOnProgressTransactionsOnService(service);
                    if (onProgressTransactionsOnService.isEmpty()) {
                        hasOnProgressTransactions = true;
                    } else {
                        hasOnProgressTransactions = false;
                    }
                    UserTable user = userDataInterFace.findById(transactionDto.getsByUser()).get();
                    List<TransactionInfo> userUnavailableTransactions = transactionDao.findUserUnavailableTransactions(user, service);
                    if (userUnavailableTransactions.isEmpty() && hasOnProgressTransactions) {
                        Integer userId = transactionDto.getsByUser();
                        TransactionInfo transactionInfo = AppFactory.mapToEntity(transactionDto, TransactionInfo.class);

                        transactionInfo.setType(service.getType());
                        transactionInfo.setState(TransactionInfo.PENDING_STATE);
                        transactionInfo.setStartDate(new Date());
                        transactionInfo.setStartedBy(user);

                        transactionDao.save(transactionInfo);
                        transactionInfo.setStartedBy(userDataInterFace.findById(userId).get());
                        transactionDto = AppFactory.mapToDto(transactionInfo, TransactionDto.class);
                        transactionDto.setsByUser(userId);

                        return transactionDto;
                    }
                }
            }
        }

        return null;
    }

    @Override
    public TransactionDto completeTransaction(TransactionDto transactionDto) {

        TransactionInfo transactionInfo = transactionDelegate.checkIfTransactionExist(transactionDto.getId());
        if (transactionInfo != null &&
                transactionInfo.getStartedBy() != null &&
                transactionInfo.getServiceId() != null) {
            Boolean done = delegate.completeTransaction(transactionInfo);
            if (done) {
                transactionInfo.setEndDate(new Date());
                transactionDao.save(transactionInfo);
                TransactionDto transactionDto2 = AppFactory.mapToDto(transactionInfo, TransactionDto.class);
                transactionDto2.setsByUser((transactionInfo.getStartedBy()).getId());
                return transactionDto2;
            }

            return null;
        }

        return null;
    }

    @Override
    public TransactionDto approveCompletedTransaction(TransactionDto transactionDto) {

        TransactionInfo transactionInfo = transactionDelegate.checkIfTransactionExist(transactionDto.getId());
        if (transactionInfo != null &&
                transactionInfo.getStartedBy() != null &&
                transactionInfo.getServiceId() != null) {
            Boolean done = delegate.approveCompletedTransaction(transactionInfo);
            if (done) {
//                transactionInfo.setEndDate(new Date());
                transactionDao.save(transactionInfo);
                TransactionDto transactionDto2 = AppFactory.mapToDto(transactionInfo, TransactionDto.class);
                transactionDto2.setsByUser((transactionInfo.getStartedBy()).getId());
                return transactionDto2;
            }

            return null;
        }

        return null;
    }

    @Override
    public TransactionDto rejectCompletedTransaction(TransactionDto transactionDto) {
        TransactionInfo transactionInfo = transactionDelegate.checkIfTransactionExist(transactionDto.getId());
        if (transactionInfo != null &&
                transactionInfo.getStartedBy() != null &&
                transactionInfo.getServiceId() != null) {
            Boolean done = delegate.rejectCompletedTransaction(transactionInfo);
            if (done) {
                transactionDao.save(transactionInfo);
                TransactionDto transactionDto2 = AppFactory.mapToDto(transactionInfo, TransactionDto.class);
                transactionDto2.setsByUser((transactionInfo.getStartedBy()).getId());
                return transactionDto2;
            }

            return null;
        }

        return null;
    }

    @Override
    public List<TransactionDto> getUserActiveTransactions(Integer userId, Integer pageNum) {
        UserTable user = userDelegateInterfaceImpl.getUserById(userId);
        if (user != null) {
            return transactionDelegate.getUserActiveTransactions(user, pageNum);
        } else {
            return null;
        }
    }

    @Override
    public List<TransactionDto> getUserCompletedTransactions(Integer userId, Integer pageNum) {
        UserTable user = userDelegateInterfaceImpl.getUserById(userId);
        if (user != null) {
            return transactionDelegate.getUserCompletedTransactions(user, pageNum);
        } else {
            return null;
        }
    }

    @Override
    public List<TransactionDto> getUserCompletedAndApprovedTransactions(Integer userId, Integer pageNum) {
        UserTable user = userDelegateInterfaceImpl.getUserById(userId);
        if (user != null) {
            return transactionDelegate.getUserCompletedAndApprovedTransactions(user, pageNum);
        } else {
            return null;
        }
    }


    ////////////////////////////Nouran////////////////////////////
}
