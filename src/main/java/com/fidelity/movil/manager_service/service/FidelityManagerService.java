package com.fidelity.movil.manager_service.service;

import com.fidelity.movil.manager_service.mapping.FidelityMangerMapping;
import com.fidelity.movil.manager_service.model.FidelityManager;
import com.fidelity.movil.manager_service.repository.FidelityManagerRepository;
import com.fidelity.movil.manager_service.request.FidelityManagerRequest;
import com.fidelity.movil.manager_service.response.FidelityResponse;
import com.fidelity.movil.manager_service.service.IMPService.IMPFidelityManagerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FidelityManagerService implements IMPFidelityManagerService {
    private final FidelityManagerRepository _fidelityManagerRepository;

    public FidelityManagerService(FidelityManagerRepository fidelityManagerRepository) {
        _fidelityManagerRepository = fidelityManagerRepository;
    }

    @Override
    public FidelityResponse create(FidelityManagerRequest fidelityManagerRequest) {
        FidelityManager oFidelityManger = FidelityMangerMapping.RequestToFidelityMager(fidelityManagerRequest);
        FidelityResponse response = new FidelityResponse();

        try {
            response.setData(_fidelityManagerRepository.save(oFidelityManger));
            response.setMessage("Mensaje de creacion de gestor");
            response.setCode(201);
        } catch (Exception e) {
            response.setData(null);
            response.setMessage(e.getMessage());
            response.setCode(404);
        }
        return response;
    }

    @Override
    public FidelityResponse findById(long id) {
        FidelityResponse response = new FidelityResponse();

        try {
            response.setData(_fidelityManagerRepository.findById(id).get());
            response.setMessage("Mensaje de busqueda de gestor");
            response.setCode(200);
        } catch (Exception e) {
            response.setData(null);
            response.setMessage(e.getMessage());
            response.setCode(404);
        }
        return response;
    }

    @Override
    public List<FidelityManager> findAllManagerByType() {
        return _fidelityManagerRepository.findAllManagersOrderByNumberOfClients();
    }


}