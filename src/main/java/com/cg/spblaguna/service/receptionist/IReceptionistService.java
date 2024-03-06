package com.cg.spblaguna.service.receptionist;

import com.cg.spblaguna.model.Receptionist;
import com.cg.spblaguna.model.dto.req.ReceptionistReqDTO;
import com.cg.spblaguna.service.IGeneralService;

import java.util.List;

public interface IReceptionistService extends IGeneralService<Receptionist,Long> {
    void create(ReceptionistReqDTO receptionistReqDTO);
    List<Receptionist> findAllByUser_Unlock(boolean user_unlock);
    Receptionist findByUser_Id(Long id);
}
