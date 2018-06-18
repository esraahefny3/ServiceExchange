package com.service_exchange.api_services.bussinessdaodelegates.user


import com.service_exchange.api_services.KotlinUtal.convert
import com.service_exchange.api_services.KotlinUtal.convertServie
import com.service_exchange.api_services.KotlinUtal.convertToServiceHoda
import com.service_exchange.api_services.KotlinUtal.convertUser
import com.service_exchange.api_services.dao.dto.*
import com.service_exchange.api_services.dao.skill.SkillInterface
import com.service_exchange.api_services.dao.transaction.TransactionDto
import com.service_exchange.api_services.dao.user.UserEducationInterface
import com.service_exchange.api_services.dao.user.UserEmailInterface
import com.service_exchange.api_services.dao.user.UserInterFace
import com.service_exchange.api_services.dao.user.UserTelephoneInterface
import com.service_exchange.api_services.dao.user.userService.UserServicesInterFace
import com.service_exchange.api_services.dao.user.userSkill.UserSkillInterFace
import com.service_exchange.api_services.dao.user.userbadge.UserBadgesInterface
import com.service_exchange.api_services.factories.AppFactory
import com.service_exchange.entities.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.atomic.AtomicReference
import java.util.function.DoubleConsumer
import java.util.function.ToDoubleFunction
import java.util.function.ToIntFunction
import java.util.stream.Collectors


interface UserDataGet {
    fun loginOrSignUp(user: UserDTO?): UserDTO?
    fun getUserSkill(userId: Int?): List<SkillDTO>
    fun getUserEdcation(userId: Int?): List<EdcationDTO>
    fun getUserServices(userId: Int?, type: String?): List<ServiceDTO>
    fun getAllUser(start: Int): List<UserDTO>
    fun getAllUser(): List<UserDTO>
    fun getUserById(userId: Int?): UserTable?
    fun getUserByID(userId: Int?): UserDTO?
    fun getUserInfoByID(userId: Int?): UserInfo?
    fun getLastActiveService(userId: Int?): ServiceHoda?
    fun getLastPausedService(userId: Int?): ServiceHoda?
    fun getLastActiveReq(userId: Int?): ServiceHoda?
    fun getLastCompletedReq(userId: Int?): ServiceHoda?
    fun getTopUser(size: Int?): List<UserDTO>
    fun getUserIncomingReq(userId: Int?): List<TransactionDto>


}


@org.springframework.stereotype.Service
private open class UserDataGetImpl : UserDataGet {
    override fun getUserIncomingReq(userId: Int?): List<TransactionDto> =
            Collections.synchronizedList(LinkedList<TransactionDto>()).apply {
                userInterface.getUser(userId)?.serviceCollection?.stream()?.filter { it.type == Service.OFFERED }
                        ?.parallel()
                        ?.forEach { t ->
                            t.transactionInfoCollection?.stream()?.filter {
                                it.state == TransactionInfo.PENDING_STATE
                                        || it.state == TransactionInfo.POSTPONED
                            }?.forEach { add(it.convert()) }
                        }
            }


    override fun getUserInfoByID(userId: Int?): UserInfo? =
            userInterface.getUser(userId)?.let {
                UserInfo(id = it.id, userName = it.name, image = it.image)
            }


    @Autowired
    lateinit var userInterface: UserInterFace
    @Autowired
    lateinit var userSkillInterFace: UserSkillInterFace
    @Autowired
    lateinit var userService: UserServicesInterFace
    @Autowired
    lateinit var userEmail: UserEmailInterface
    @Autowired
    lateinit var userTelephoneInterface: UserTelephoneInterface
    @Autowired
    lateinit var userBadgeInterFace: UserBadgesInterface

    override fun getLastActiveService(userId: Int?): ServiceHoda? =
            userService.getUserServices(userId)?.stream()?.filter { it.type == Service.OFFERED }
                    ?.filter { it.available == Service.AVALIBLE }?.sorted(compareBy { it.startDate?.time })
                    ?.findFirst()?.let { optional ->
                        if (optional.isPresent)
                            return@let optional.get().convertToServiceHoda()
                        else null
                    }

    override fun getLastPausedService(userId: Int?): ServiceHoda? =
            userService.getUserServices(userId)?.stream()?.filter { it.type == Service.OFFERED }
                    ?.filter { it.available == Service.PAUSED }?.sorted(compareBy { it.startDate?.time })
                    ?.findFirst()?.let { optional ->
                        if (optional.isPresent)
                            return@let optional.get().convertToServiceHoda()
                        else null
                    }

    override fun getLastActiveReq(userId: Int?): ServiceHoda? =
            userService.getUserServices(userId)?.stream()?.filter { it.type == Service.REQUSETED && it.available == Service.AVALIBLE }
                    ?.filter {
                        if (it?.transactionInfoCollection?.size == 0)
                            return@filter true
                        it?.transactionInfoCollection?.stream()
                                ?.anyMatch { t -> t.state != TransactionInfo.COMPLETED_STATE || t.state != TransactionInfo.LATE_STATE }
                                ?: true
                    }?.sorted(compareBy { it.startDate?.time })
                    ?.findFirst()?.let { optional ->
                        if (optional.isPresent)
                            return@let optional.get().convertToServiceHoda()
                        else null
                    }

    override fun getLastCompletedReq(userId: Int?): ServiceHoda? =
            userService.getUserServices(userId)?.stream()?.filter { it.type == Service.REQUSETED }
                    ?.filter {
                        it?.transactionInfoCollection?.stream()
                                ?.anyMatch { t -> t.state == TransactionInfo.COMPLETED_STATE || t.state == TransactionInfo.LATE_STATE }
                                ?: false
                    }?.sorted(compareBy { it.startDate?.time })
                    ?.findFirst()?.let { optional ->
                        if (optional.isPresent)
                            return@let optional.get().convertToServiceHoda()
                        else null
                    }

    override fun getTopUser(size: Int?): List<UserDTO> =
            userInterface.allUser.stream().filter { it.transactionInfoCollection?.size ?: 0 > 0 }
                    ?.sorted(compareByDescending {
                        it.transactionInfoCollection?.stream()
                                ?.mapToDouble { value ->
                                    value.reviewCollection?.stream()
                                            ?.mapToDouble { it.rating?.toDouble() ?: 0.0 }?.average()
                                            ?.let { if (it.isPresent) it.asDouble else 0.0 } ?: 0.0
                                }
                                ?.average().let {
                                    if (it != null && it.isPresent)
                                        it.asDouble
                                    else 0.0
                                }
                    })?.map { it.convertUser() }?.collect(Collectors.toList())?.take(size ?: 0) ?: emptyList()

    override fun getUserByID(userId: Int?): UserDTO? =
            userInterface.getUser(userId)?.convertUser()

    override fun getUserById(userId: Int?): UserTable? =
            userInterface.getUser(userId)

    override fun getAllUser(start: Int): List<UserDTO> {
        return userInterface.getAllUser(start).stream().map { it.convertUser() }.collect(Collectors.toList())
                ?: emptyList()
    }

    override fun getAllUser(): List<UserDTO> =
            userInterface.getAllUser().toList().stream().map { it.convertUser() }.collect(Collectors.toList())


    override fun loginOrSignUp(user: UserDTO?): UserDTO? =
            if (user != null) {
                val userDTO = AppFactory.mapToDto(user, UserTable::class.java)

                userDTO.birthDate = user.bD?.let { Date(user.bD ?: 0) }

                userDTO.userEmailCollection = mutableListOf()
                userDTO.userTelephoneCollection = mutableListOf()

                var retVal = userInterface.createUser(userDTO)
                if (retVal != null) {
                    if (retVal.frist) {
                        user.userEmailCollection?.forEach { retVal?.addEmail(retVal?.id, it) }
                        user.UserTelephone?.forEach { retVal?.addTelephone(retVal?.id, it) }
                        retVal.signUpDate = Date()
                        retVal.description = ""
                        retVal.bio = ""
                        retVal.userAuthority = UserAuthority()
                        retVal.userAuthority?.authority = "ROLE_User"
                        retVal.userAuthority?.userId = retVal.id
                        retVal.balance = 25
                        retVal.enabled = UserTable.ENABELED
                        retVal.lastPasswordChanged = Date()
                        userBadgeInterFace.assignBadgeToUser(retVal.id ?: 0, 1);
                        retVal = userInterface.updateUser(retVal);

                    }
                    if (retVal != null)
                        retVal.convertUser()
                    else null


                } else null

            } else null


    override fun getUserSkill(userId: Int?): List<SkillDTO> {
        if (userId != null) {

            val userSkill = userSkillInterFace.getUserSkill(userId)?.stream()?.map { AppFactory.mapToDto(it, SkillDTO::class.java) }?.collect(Collectors.toList())
                    ?: emptyList<SkillDTO>()


            return userSkill
        }
        return emptyList()
    }

    override fun getUserEdcation(userId: Int?): List<EdcationDTO> {
        return userInterface.getEducation(userId)?.stream()?.map { AppFactory.mapToDto(it, EdcationDTO::class.java) }?.collect(Collectors.toList())
                ?: emptyList()
    }

    override fun getUserServices(userId: Int?, type: String?): List<ServiceDTO> {
        return userService.getUserServices(userId)?.stream()?.filter { it.type == type }
                ?.filter { it.available != null && it.available != Service.DELETED }?.map { AppFactory.mapToDto(it, ServiceDTO::class.java) }?.collect(Collectors.toList())
                ?: emptyList()
    }

}

interface UserDataSet {
    fun addEmailToUser(email: String?, userId: Int?): Boolean
    fun addEdcationToUser(edcationDTO: EdcationDTO?, userId: Int?): Boolean
    fun addSkillToUser(skillDTO: SkillDTO?, userId: Int?): Boolean
    fun addTelephonToUser(telephone: String?, userId: Int?): Boolean
    fun addServiceToUser(serviceDTO: ServiceDTO?): ServiceDTO?
    fun setUserFirebase(userId: Int?, firebase: String?, type: String?): Boolean
    fun updateUserDate(userDto: UserDTO?): Boolean
    fun updateUserService(serviceDTO: ServiceDTO?): Boolean
}

@Component
private class UserDataSetImol : UserDataSet {

    @Autowired
    lateinit var userSkillInterFace: UserSkillInterFace
    @Autowired
    lateinit var userService: UserServicesInterFace
    @Autowired
    lateinit var userEmail: UserEmailInterface
    @Autowired
    lateinit var userEdcation: UserEducationInterface
    @Autowired
    lateinit var userTelephone: UserTelephoneInterface
    @Autowired
    lateinit var userInterFace: UserInterFace
    @Autowired
    lateinit var skillINterface: SkillInterface

    override fun setUserFirebase(userId: Int?, firebase: String?, type: String?): Boolean =
            if (userId != null)
                userInterFace.getUser(userId)?.let {
                    val userFirebaseToken = UserFirebaseToken()
                    userFirebaseToken.userTable = it
                    userFirebaseToken.userFirebaseTokenPK = UserFirebaseTokenPK()
                    userFirebaseToken.userFirebaseTokenPK?.type = type
                    userFirebaseToken.userFirebaseTokenPK?.token = firebase
                    userFirebaseToken.userFirebaseTokenPK?.userId = userId
                    it.userFirebaseTokenCollection.add(userFirebaseToken)
                    userInterFace.updateUser(it)
                    true
                } ?: false
            else
                false

    override fun addEmailToUser(email: String?, userId: Int?): Boolean =
            if (userId != null && email != null) {
                userEmail.addEmail(email, userId)
                true
            } else {
                false
            }


    override fun addEdcationToUser(edcationDTO: EdcationDTO?, userId: Int?): Boolean =
            if (userId != null && edcationDTO != null) {
                val edcation = AppFactory.mapToDto(edcationDTO, Education::class.java)
                userEdcation.addEducation(edcation, userId)
                true
            } else {
                false
            }

    override fun addSkillToUser(skillDTO: SkillDTO?, userId: Int?): Boolean =
            if (userId != null && skillDTO != null) {
                val skill = AppFactory.mapToDto(skillDTO, Skill::class.java)
                userSkillInterFace.addSkill(skill, userId)
                true
            } else {
                false
            }

    override fun addTelephonToUser(telephone: String?, userId: Int?): Boolean {
        return userTelephone.addTelephoneToUser(telephone, userId)
    }

    override fun addServiceToUser(serviceDTO: ServiceDTO?): ServiceDTO? {
        return if (serviceDTO != null && serviceDTO.uO != null) {
            val service = serviceDTO.convertServie(skillINterface, userInterface = userInterFace)
            if (serviceDTO.price != null)
                service.available = Service.AVALIBLE
            else
                service.available = Service.PAUSED
            service.startDate = Date()
            userService.addServiceToUser(serviceDTO.uO?.id, service)?.convertServie()
        } else null

    }

    override fun updateUserDate(userDto: UserDTO?): Boolean =
            userInterFace.getUser(userDto?.id)?.let {
                it.name = userDto?.name
                it.image = userDto?.image
                it.address = userDto?.address
                it.bio = userDto?.bio
                it.status = userDto?.status ?: UserTable.ONLINE
                it.description = userDto?.descrption
                userInterFace.updateUser(it)
                true
            } ?: false

    override fun updateUserService(serviceDTO: ServiceDTO?): Boolean {
        println(serviceDTO)
        return userService.getServiceById(serviceDTO?.id)?.let {
            it.duration = serviceDTO?.duration?.toInt()
            it.description = serviceDTO?.description
            it.image = serviceDTO?.image
            it.name = serviceDTO?.name
            it.price = serviceDTO?.price
            userService.updateService(it)


        } ?: false
    }


}

interface UserDataDelete {
    fun removeEmailFormUser(email: String?, userId: Int?): Boolean
    fun removeEdcationFromUser(edcationId: Int?, userId: Int?): Boolean
    fun removeSkillFormUser(skillId: Int?, userId: Int?): Boolean
    fun removeTelePhoneFormUser(telephone: String?, userId: Int?): Boolean
    fun removeServiceToUser(userId: Int?, serviceId: Int?, forced: Boolean?): Boolean
}

@Component
private class UserDataDeletImpel : UserDataDelete {
    @Autowired
    lateinit var userSkillInterFace: UserSkillInterFace
    @Autowired
    lateinit var userService: UserServicesInterFace
    @Autowired
    lateinit var userEmail: UserEmailInterface
    @Autowired
    lateinit var userEdcation: UserEducationInterface
    @Autowired
    lateinit var userTelephone: UserTelephoneInterface

    override fun removeEmailFormUser(email: String?, userId: Int?): Boolean =
            if (email != null && userId != null) {
                userEmail.removeEmail(email, userId)

            } else false


    override fun removeEdcationFromUser(edcationId: Int?, userId: Int?): Boolean =
            if (edcationId != null && userId != null) {
                userEdcation.removeEducation(educationId = edcationId, userId = userId)

            } else false

    override fun removeSkillFormUser(skillId: Int?, userId: Int?): Boolean =
            if (skillId != null && userId != null) {
                userSkillInterFace.deleteSkill(skillId, userId)

            } else false

    override fun removeTelePhoneFormUser(telephone: String?, userId: Int?): Boolean =
            userTelephone.removeTelephoneFromUser(telephone, userId)


    override fun removeServiceToUser(userId: Int?, serviceId: Int?, forced: Boolean?): Boolean =
            userService.removeServiceForUser(userId, serviceId, forced)

}

@Component
class UserStaticsGetter {
    @Autowired
    lateinit var userDataGet: UserDataGet

    fun getEarning(userId: Int, localDate: Calendar): Double {
        val userTable = userDataGet.getUserById(userId)

        //localDate.set(Calendar.DAY_OF_MONTH, 1);
        return if (userTable != null) {
            userTable.serviceCollection?.stream()?.filter({ service -> service.type == com.service_exchange.entities.Service.OFFERED })
                    ?.mapToInt({ value ->
                        value.transactionInfoCollection?.stream()?.filter({ transactionInfo -> transactionInfo.state == TransactionInfo.COMPLETED_STATE || transactionInfo.state == TransactionInfo.LATE_STATE })
                                ?.filter({ transactionInfo ->
                                    transactionInfo.startDate?.time ?: 0 + (transactionInfo.duration?.toLong()
                                            ?: 0) >= localDate.timeInMillis
                                })

                                ?.mapToInt({ it.price ?: 0 })?.sum() ?: 0
                    }
                    )?.sum()?.toDouble() ?: 0.0
        } else
            0.0
    }

    fun getAVGEarning(userId: Int): Double {
        val userTable = userDataGet.getUserById(userId)
        val d = doubleArrayOf(0.0)
        //localDate.set(Calendar.DAY_OF_MONTH, 1);
        if (userTable != null) {
            userTable.serviceCollection?.stream()?.filter { service -> service.type == com.service_exchange.entities.Service.OFFERED }
                    ?.mapToDouble { value ->
                        value.transactionInfoCollection?.stream()?.filter { transactionInfo -> transactionInfo.state == TransactionInfo.COMPLETED_STATE || transactionInfo.state == TransactionInfo.LATE_STATE }


                                ?.mapToInt(ToIntFunction<TransactionInfo> {
                                    it.getPrice() ?: 0
                                })?.average()?.ifPresent { value1 -> d[0] = value1 }
                        d[0]
                    }?.average()?.ifPresent { value -> d[0] = value }
            return d[0]
        } else
            return 0.0
    }

    fun getUserBalance(userId: Int): Int {
        val userTable = userDataGet.getUserById(userId)
        return if (userTable != null) {
            userTable.balance ?: 0
        } else
            0
    }

    fun getUserBalanceFormTheStart(userId: Int): Int {
        val userTable = userDataGet.getUserById(userId)
        return if (userTable != null) {
            val t = userTable.serviceCollection?.stream()
                    ?.mapToInt { value ->

                        value.transactionInfoCollection?.stream()?.filter { transactionInfo -> transactionInfo.state != TransactionInfo.LATE_STATE && transactionInfo.state != TransactionInfo.COMPLETED_STATE }


                                ?.mapToInt(ToIntFunction<TransactionInfo> {
                                    it.getPrice() ?: 0
                                })?.sum() ?: 0

                    }?.sum() ?: 0

            t + getUserBalance(userId)
        } else
            0
    }

    fun getOrderCompletion(userId: Int?): Double {
        val userTable = userDataGet.getUserById(userId)
        val d = AtomicReference(0.toDouble())
        userTable?.serviceCollection?.stream()?.filter { service -> service.type == com.service_exchange.entities.Service.OFFERED }
                ?.mapToDouble { service ->
                    service.transactionInfoCollection?.stream()?.filter { transactionInfo ->
                        transactionInfo.state != TransactionInfo.REJECTED_STATE
                                && transactionInfo.state != TransactionInfo.PENDING_STATE
                    }
                            ?.mapToDouble { value ->
                                if (value.state == TransactionInfo.ACCEPTED_STATE || value.state == TransactionInfo.ON_PROGRESS_STATE) {
                                    0.0
                                } else
                                    1.0
                            }?.average()?.ifPresent(DoubleConsumer { d.set(it) })
                    d.get()
                }?.average()?.ifPresent(DoubleConsumer { d.set(it) })
        return d.get()
    }

    fun getOnTimeDelevrey(userId: Int?): Double {
        val userTable = userDataGet.getUserById(userId)
        val d = AtomicReference(0.toDouble())
        userTable?.serviceCollection?.stream()?.filter { service -> service.type == com.service_exchange.entities.Service.OFFERED }?.mapToDouble { service ->
            service.transactionInfoCollection?.stream()?.filter { transactionInfo ->
                (transactionInfo.state == TransactionInfo.COMPLETED_STATE
                        || transactionInfo.state == TransactionInfo.EXTENDED_STATE
                        || transactionInfo.state == TransactionInfo.LATE_STATE)
            }
                    ?.mapToDouble { value ->
                        if (value.state == TransactionInfo.LATE_STATE || value.state == TransactionInfo.EXTENDED_STATE) {
                            0.0
                        } else
                            1.0
                    }?.average()?.ifPresent(DoubleConsumer { d.set(it) })
            d.get()
        }?.average()?.ifPresent(DoubleConsumer { d.set(it) })
        return d.get()
    }

    fun getTotalFeedBack(userId: Int?): Double {
        val userTable = userDataGet.getUserById(userId)
        val d = AtomicReference(0.toDouble())
        userTable?.serviceCollection?.stream()?.filter { service -> service.type == com.service_exchange.entities.Service.OFFERED }?.mapToDouble { service ->
            service.transactionInfoCollection?.stream()?.filter { transactionInfo ->
                (transactionInfo.state == TransactionInfo.COMPLETED_STATE
                        || transactionInfo.state == TransactionInfo.EXTENDED_STATE
                        || transactionInfo.state == TransactionInfo.LATE_STATE)
            }
                    ?.mapToDouble { value ->
                        val `val` = doubleArrayOf(0.0)
                        value.reviewCollection?.stream()?.mapToDouble(ToDoubleFunction<Review> {
                            (it?.rating?.toDouble() ?: 0.0)
                        })?.average()?.ifPresent { value1 -> `val`[0] = value1 / 5 }
                        `val`[0]
                    }?.average()?.ifPresent(DoubleConsumer { d.set(it) })
            d.get()
        }?.average()?.ifPresent(DoubleConsumer { d.set(it) })
        return d.get()
    }

    fun getResponceMessageTime(userId: Int?): Double {
        val userTable = userDataGet.getUserById(userId)
        val retval = doubleArrayOf(0.0)
        if (userTable != null) {
            var d = 0.0;
            userTable.serviceCollection?.stream()?.filter { service -> service.type == com.service_exchange.entities.Service.OFFERED }
                    ?.map { service -> service?.getTransactionInfoCollection()?.stream() }
                    ?.mapToDouble { value ->
                        val dval = doubleArrayOf(0.0);
                        value?.mapToDouble {
                            it.messageCollection?.stream()?.filter { message -> message?.senderId?.id != userId }
                                    ?.mapToDouble { value -> value.seenDate?.time?.toDouble() ?: 0.0 }?.sorted()
                                    ?.reduce(0.0, { left, right -> right - left })
                                    ?.toDouble() ?: 0.0


                        }?.average()?.ifPresent(DoubleConsumer { dval[0] = it });
                        return@mapToDouble dval[0];
                    }?.average()?.ifPresent { d = it }
            return d;


        }
        return 0.0
    }

    fun getResponceRate(userId: Int?): Double {
        val userTable = userDataGet.getUserById(userId)
        val d = AtomicReference(0.toDouble())
        userTable?.serviceCollection?.stream()?.filter { service -> service.type == com.service_exchange.entities.Service.OFFERED }?.mapToDouble { service ->
            service.transactionInfoCollection?.stream()?.filter { transactionInfo -> transactionInfo.state != TransactionInfo.REJECTED_STATE }
                    ?.mapToDouble { value ->

                        if (value.state == TransactionInfo.PENDING_STATE || value.state == TransactionInfo.POSTPONED) {
                            0.0
                        } else
                            1.0
                    }?.average()?.ifPresent(DoubleConsumer { d.set(it) })
            d.get()
        }?.average()?.ifPresent(DoubleConsumer { d.set(it) })
        return d.get()
    }

    fun getActiveOrderCount(userId: Int?): ActiveOrder {
        val userTable = userDataGet.getUserById(userId)
        val activeOrder = ActiveOrder(0, 0)
        userTable?.serviceCollection?.stream()?.filter { service -> service.type == com.service_exchange.entities.Service.OFFERED }?.forEach { service ->
            service.transactionInfoCollection?.stream()?.filter { transactionInfo ->
                (transactionInfo.state == TransactionInfo.ACCEPTED_STATE
                        || transactionInfo.state == TransactionInfo.ON_PROGRESS_STATE || transactionInfo.state == TransactionInfo.EXTENDED_STATE)
            }
                    ?.forEach { value ->

                        activeOrder.orderCount = activeOrder.orderCount + 1
                        activeOrder.ordersValue = activeOrder.ordersValue + (value.price ?: 0)
                    }

        }
        return activeOrder
    }

    fun getEaringList(userId: Int?): List<EarningListObject> {
        val userTable = userDataGet.getUserById(userId)
        val list = ArrayList<EarningListObject>()
        userTable?.serviceCollection?.stream()?.filter { service -> service.type == com.service_exchange.entities.Service.OFFERED }?.forEach { service ->
            service?.transactionInfoCollection?.stream()?.filter { transactionInfo ->
                (transactionInfo?.state == TransactionInfo.ACCEPTED_STATE
                        || transactionInfo?.state == TransactionInfo.COMPLETED_STATE || transactionInfo?.state == TransactionInfo.LATE_STATE)
            }
                    ?.forEach { transactionInfo ->
                        list.add(EarningListObject(transactionInfo.id,
                                transactionInfo?.serviceId?.name, transactionInfo?.startDate?.time ?: 0
                        +(transactionInfo?.duration?.toLong() ?: 0), transactionInfo?.price))
                    }
        }
        return list
    }

    fun getUserLevel(userId: Int): String? {
        val userTable = userDataGet.getUserById(userId)
        var level: String? = null
        if (userTable != null) {
            level = userTable.userBadgeCollection?.stream()?.filter { userBadge ->
                val b = userBadge.badge.nextLevel
                b?.userBadgeCollection?.stream()?.noneMatch { userBadge1 -> userBadge1.userBadgePK.userId == userId }
                        ?: true
            }?.findFirst()?.map { userBadge -> userBadge.badge.name }?.orElse("not found")

        }
        return level
    }

    fun getUserNextLevel(userId: Int): String? {
        val userTable = userDataGet.getUserById(userId)
        var level: String? = null
        if (userTable != null) {
            level = userTable.userBadgeCollection?.stream()?.filter { userBadge ->
                val b = userBadge.badge.nextLevel
                b?.userBadgeCollection?.stream()?.noneMatch { userBadge1 -> userBadge1.userBadgePK.userId == userId }
                        ?: false
            }?.findFirst()?.map { userBadge -> userBadge.badge.nextLevel.description }?.orElse("not found")

        }
        return level
    }

}