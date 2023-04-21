/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.programa08CC;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Angel
 */
@RestController
@RequestMapping(path = "/api")
public class EmpleadoController {
    @Autowired
    EmpleadoRepository empRep;
    
    @GetMapping("/hello")
    public String hello(){
        return "Hola mundo";
    }
    
    @GetMapping("/empleado/{id}")
    public DTOEmpleado findById(@PathVariable("id") int id){
        Optional<Empleado> res= empRep.findById(id);
        DTOEmpleado emp= null;
        
        if(res.isPresent()){
            emp= new DTOEmpleado();
            emp.setClave(res.get().getClave());
            emp.setNombre(res.get().getNombre());
            emp.setDireccion(res.get().getDireccion());
            emp.setTelefono(res.get().getTelefono());
        }
        
        return emp;
    }
    
    @GetMapping("/empleado")
    public List<DTOEmpleado> findAll(){
        List<DTOEmpleado> emps= new ArrayList<>();
        
        Iterable<Empleado> res= empRep.findAll();
        for (Iterator<Empleado> iterator = res.iterator(); iterator.hasNext();) {
            Empleado emp= iterator.next();
            DTOEmpleado dtoEmp = new DTOEmpleado();
            dtoEmp.setClave(emp.getClave());
            dtoEmp.setNombre(emp.getNombre());
            dtoEmp.setDireccion(emp.getDireccion());
            dtoEmp.setTelefono(emp.getTelefono());
            emps.add(dtoEmp);
            
        }
        
        return emps;
    }
    
    @PostMapping("/empleado")
    public DTOEmpleado create(@RequestBody Empleado empleado){
        Empleado emp= new Empleado();
        emp.setClave(empleado.getClave());
        emp.setNombre(empleado.getNombre());
        emp.setDireccion(empleado.getDireccion());
        emp.setTelefono(empleado.getTelefono());
        Empleado empNew= empRep.save(emp);
        
        DTOEmpleado dtoEmpNew= new DTOEmpleado();
        dtoEmpNew.setClave(empNew.getClave());
        dtoEmpNew.setNombre(empNew.getNombre());
        dtoEmpNew.setDireccion(empNew.getDireccion());
        dtoEmpNew.setTelefono(empNew.getTelefono());
        
        return dtoEmpNew;
    }
    
}
