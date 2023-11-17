//crenças iniciais do paciente
estado(calmo).

!start.
+!start : true  <- 
    .print("pronto para a simulação.......").

+evento(barulho): turno(madrugada) & clima(ventania)  
    <- 
       .print("vou gritar de medo");
       -estado(calmo);
       +estado(agitado);
       som(gritar).

+evento(barulho): turno(madrugada)
    <-
       .print("vou levantar");
       movimento(levantar).

+evento(visita): turno(noite)
    <-
       .print("vou levantar");
       movimento(levantar).

+evento(barulho): true
    <-
       .print("vou chamar conhecido");
       som(gritar).

+tv(ligada): estado(calmo)
    <-
    .print("..... estou bem").

+tv(ligada): estado(agitado)
    <-
    som(gritar);
    .send(cuidador,tell,paciente(grito)).

+evento(barulho): turno(noite)
    <-
       .print("vou sacudir");
       movimento(sacudir).