!start.
+!start : true  <- 
    .print("pronto para a simulação.......").

+movimento(levantar): (turno(noite) | turno(madrugada))
    <- .print("sensoriei que o paciente levantou");
       .send(atuadorSmartTV,tell,levanta);
       .send(atuadorIluminacao,tell,levanta);
       -tv(desligada).  

+movimento(levantar) : true  
    <- .print("sensoriei que o paciente levantou");
       .send(atuadorSmartTV,tell,levanta);
       -tv(desligada).  


+movimento(sacudir) : true  
    <- .print("sensoriei que o paciente sacudindo");
       .send(atuadorSmartTV,tell,sacode);
       -tv(desligada).  