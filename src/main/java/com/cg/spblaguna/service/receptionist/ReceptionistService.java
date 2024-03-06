package com.cg.spblaguna.service.receptionist;

import com.cg.spblaguna.model.enumeration.ELockStatus;
import com.cg.spblaguna.model.enumeration.ERole;
import com.cg.spblaguna.model.Receptionist;
import com.cg.spblaguna.model.User;
import com.cg.spblaguna.model.dto.req.ReceptionistReqDTO;
import com.cg.spblaguna.repository.IReceptionistRepository;
import com.cg.spblaguna.repository.IUserRepository;
//import com.cg.spblaguna.util.EmailUtil;
import com.cg.spblaguna.util.PasswordEncryptionUtil;
import com.cg.spblaguna.util.RandomCode;
import com.cg.spblaguna.util.SendEmail;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReceptionistService implements IReceptionistService {
    @Autowired
    private IReceptionistRepository receptionistRepository;
    @Autowired
    private IUserRepository iUserRepository;
//    @Autowired
//    private EmailUtil emailUtil;
    @Override
    public List<Receptionist> findAll() {
        return receptionistRepository.findAll();
    }

    @Override
    public Optional<Receptionist> findById(Long id) {
        return receptionistRepository.findById(id);
    }

    @Override
    public void save(Receptionist receptionist) {
        receptionistRepository.save(receptionist);
    }

    @Override
    public void deleteById(Long id) {
        receptionistRepository.deleteById(id);
    }

    @Override
    public void create(ReceptionistReqDTO receptionistReqDTO) {
        // Tạo mật khẩu ngẫu nhiên
        String password = RandomCode.generateRandomCode(6);

        // Tạo đối tượng User mới
        User user = new User();
        user.setEmail(receptionistReqDTO.getEmail());
        user.setPassword(PasswordEncryptionUtil.encryptPassword(password));
        user.setRole(ERole.RECEPTIONIST);
        user.setUnlock(true);

        // Lưu thông tin người dùng vào cơ sở dữ liệu
        iUserRepository.save(user);

        // Tạo tiêu đề và nội dung email
        String title = "Chúc mừng! Tài khoản đã được tạo thành công";
        String body = SendEmail.EmailRegisterDoctor(receptionistReqDTO.getReceptionistName(), password, receptionistReqDTO.getEmail());

        // Tạo đối tượng Receptionist mới
        Receptionist receptionist = new Receptionist();
        receptionist.setDob(receptionistReqDTO.getDob());
        receptionist.setEmail(receptionistReqDTO.getEmail());
        receptionist.setReceptionistName(receptionistReqDTO.getReceptionistName());
        receptionist.setCreateAt(LocalDate.now());
        receptionist.setPhone(receptionistReqDTO.getPhone());
        receptionist.setUser(user);

        receptionist.setLockStatus(ELockStatus.UNLOCK);


        // Lưu thông tin lễ tân vào cơ sở dữ liệu
        receptionistRepository.save(receptionist);

    }

    public void update(ReceptionistReqDTO receptionistReqDTO){

    }


    @Override
    public List<Receptionist> findAllByUser_Unlock(boolean user_unlock) {
        return receptionistRepository.findAllByUser_Unlock(user_unlock);
    }

    public List<Receptionist> findReceptionistsWithFilters(String receptionistName) {
        return receptionistRepository.findReceptionistsWithFilters(receptionistName);
    }

    @Override
    public Receptionist findByUser_Id(Long id) {
        return receptionistRepository.findByUser_Id(id);
    }

    public void delete(Receptionist deleteReceptionist) {
        receptionistRepository.delete(deleteReceptionist);
    }
}
