!start.
+!start : true  <- 
    .print("pronto para a simulação.......").

+levanta[source(Agt)] : turno(noite) | turno(madrugada)
    <- 
        .print(Agt,  " me acionou");
        ligarIluminacao.