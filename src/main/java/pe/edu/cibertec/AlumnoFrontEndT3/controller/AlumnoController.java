package pe.edu.cibertec.AlumnoFrontEndT3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.AlumnoFrontEndT3.client.AlumnosClient;
import pe.edu.cibertec.AlumnoFrontEndT3.dto.AlmnoResponseDTO;
import pe.edu.cibertec.AlumnoFrontEndT3.dto.AlumnoRequestDTO;
import pe.edu.cibertec.AlumnoFrontEndT3.model.AlumnoModel;

@Controller
@RequestMapping("/busqueda")
public class AlumnoController {

    @Autowired
    AlumnosClient alumnosClient;


    @GetMapping("/inicio")
    public String buscarCodigoAlumno(Model model){
        AlumnoModel alumnoModel=new AlumnoModel("","","","","");
        model.addAttribute("alumnoModel",alumnoModel);

        return "home";
    }




    @PostMapping("/encontrar")
    public String AlumnoBuscar(@RequestParam("codigo")String codigo,Model model){


        if (codigo == null || codigo.trim().length()==0){
            AlumnoModel alumnoModel=new AlumnoModel("01","Error ","","","");
            model.addAttribute("alumnoModel",alumnoModel);

            return "home";
        }


        try {
            AlumnoRequestDTO alumnoRequestDTO=new AlumnoRequestDTO(codigo);
            ResponseEntity<AlmnoResponseDTO> responseEntity=alumnosClient.buscar(alumnoRequestDTO);

            if (responseEntity.getStatusCode().is2xxSuccessful()){
                AlmnoResponseDTO almnoResponseDTO=responseEntity.getBody();

                if (almnoResponseDTO.codigo()!=null){

                    AlumnoModel alumnoModel=new AlumnoModel(almnoResponseDTO.codigo()
                            ,almnoResponseDTO.Nombre(),almnoResponseDTO.Apellido(),almnoResponseDTO.Carrera(),almnoResponseDTO.Ciclo());
                    model.addAttribute("alumnoModel",alumnoModel);

                    return "mostrar";
                }else {
                    AlumnoModel alumnoModel=new AlumnoModel("02","Error ","","","");
                    model.addAttribute("alumnoModel",alumnoModel);

                    return "home";
                }

            }else {
                AlumnoModel alumnoModel=new AlumnoModel("01","Error ","","","");
                model.addAttribute("alumnoModel",alumnoModel);

                return "home";
            }
        }catch (Exception e){
            AlumnoModel alumnoModel=new AlumnoModel("99","Error ","","","");
            model.addAttribute("alumnoModel",alumnoModel);

            return "home";
        }
    }


}
