package pe.edu.cibertec.AlumnoFrontEndT3.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pe.edu.cibertec.AlumnoFrontEndT3.dto.AlmnoResponseDTO;
import pe.edu.cibertec.AlumnoFrontEndT3.dto.AlumnoRequestDTO;

@FeignClient(name = "buscarCod",url="http://localhost:8080/busqueda")
public interface AlumnosClient {

    @PostMapping("/buscar")
    ResponseEntity<AlmnoResponseDTO> buscar(@RequestBody AlumnoRequestDTO alumnoRequestDTO);

}
