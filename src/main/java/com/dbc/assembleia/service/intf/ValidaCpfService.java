package com.dbc.assembleia.service.intf;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ValidaCpfService", url = "https://user-info.herokuapp.com/users/")
public interface ValidaCpfService {

    @GetMapping(value = "/{cpf}")
    String validaCpf(@PathVariable("cpf") String cpf);

}
