tv(desligada).

!start.

+!start : true  <- 
    .print("pronto para a simulação.......").


+som(gritar) : true  
    <- .print("sensoriei um grito do paciente");
       .send(atuadorSmartTV,tell,grito);
       -tv(desligada).

    