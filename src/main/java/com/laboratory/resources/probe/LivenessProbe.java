package com.laboratory.resources.probe;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/health/liveness")
public class LivenessProbe {

    @GetMapping(produces = "application/json")
    ResponseEntity<String> liveness() {
        return ResponseEntity.ok().build();
    }
}
//    @Value("${todo-list.divider}")
//    private Integer divider;
//
//    private Integer executeBusinessTestProcess(){
//        return 100/divider;
//    }
//
//
//
//    ResponseEntity<Status> livenessBackup(){
//        Boolean up = true;
//        List<Check> checkList = new ArrayList<>();
//        checkList.add(new Check(true, "app online, respondendo a chamados"));
//
//        try{
//            executeBusinessTestProcess();
//            checkList.add(new Check(true, "app online, estado de dados consistente"));
//        }catch (ArithmeticException invalidState){
//            checkList.add(new Check(false, "app online, estado de dados inconsistente"));
//            up = false;
//        }catch (Exception e){
//            checkList.add(new Check(false, "app online, estado da aplicacao inconsistente"));
//            up = false;
//        }
//
//        Status status = new Status(up, checkList);
//        return new ResponseEntity<>(
//                status,
//                up ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//}