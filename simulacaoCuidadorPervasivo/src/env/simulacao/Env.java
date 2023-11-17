package simulacao;

// Environment code for project simulacaoCuidadorPervasivo
import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;

import java.util.Random;
import java.util.logging.*;

public class Env extends Environment {

    private Logger logger = Logger.getLogger("simulacaoCuidadorPervasivo."+ Env.class.getName());

    public String endereco_tv = "http://10.0.0.101:8081/zeroconf/switch";
	public String endereco_iluminacao = "http://10.0.0.101:8081/zeroconf/switch";


    public String gerarTurno() {
        Random gerador = new Random();
        switch (gerador.nextInt(4)) {
            case 0:
                return "turno(manha)";
            case 1:
                return "turno(tarde)";
            case 2:
                return "turno(noite)";
            case 3:
                return "turno(madrugada)";        
            default:
                break;
        }
        return "";
    }

    public String gerarEventoExterno() {
        Random gerador = new Random();
        switch (gerador.nextInt(3)) {            
            case 0:
                return "evento(barulho)"; //buzina, cachorro, fogos de artificio
            case 1:
                return "evento(visita)"; 
            case 2:
                return "evento(nenhum)";        
            default:
                break;
        }
        return "";
    }

    public String gerarClima() {
        Random gerador = new Random();
        switch (gerador.nextInt(4)) {
            case 0:
                return "clima(chuva)";
            case 1:
                return "clima(tempestade)";
            case 2:
                return "clima(normal)";
            case 3:
                return "clima(ventania)";        
            default:
                break;
        }
        return "";
    }

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);0
        try {
           /* addPercept(ASSyntax.parseLiteral(gerarTurno()));
            addPercept(ASSyntax.parseLiteral(gerarEventoExterno()));
            addPercept(ASSyntax.parseLiteral(gerarClima()));*/

           /* addPercept(ASSyntax.parseLiteral("turno(madrugada)"));
            addPercept(ASSyntax.parseLiteral("evento(barulho)"));
            addPercept(ASSyntax.parseLiteral("clima(normal)"));*/

            addPercept(ASSyntax.parseLiteral("turno(noite)"));
            addPercept(ASSyntax.parseLiteral("evento(visita)"));
            addPercept(ASSyntax.parseLiteral("clima(normal)"));

            /*addPercept(ASSyntax.parseLiteral("turno(manha)"));
            addPercept(ASSyntax.parseLiteral("evento(barulho)"));
            addPercept(ASSyntax.parseLiteral("clima(tempestade)"));*/

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        if (agName.equals("paciente") && action.getFunctor().equals("som") && action.getTerm(0).toString().equals("gritar")){
            logger.info("paciente está gritando");
            try {
                addPercept(ASSyntax.parseLiteral("som(gritar)"));
            } catch (ParseException e) {
                e.printStackTrace();
            }                         
        } else if (agName.equals("paciente") && action.getFunctor().equals("movimento") && action.getTerm(0).toString().equals("levantar")){
            logger.info("paciente está levantando");
            try {
                addPercept(ASSyntax.parseLiteral("movimento(levantar)"));
            } catch (ParseException e) {
                e.printStackTrace();
            }             
        } else if (agName.equals("atuadorSmartTV") && action.getFunctor().equals("ligarTV")){
            logger.info("atuador da TV vai acionar sonoff...");
            //chamada do sonoff para ligar TV
            Comunicacao.Sonoff(endereco_tv, "on", 12, null);
            try {
                addPercept(ASSyntax.parseLiteral("tv(ligada)"));
            } catch (ParseException e) {
                e.printStackTrace();
            }                           
        } else if (agName.equals("atuadorIluminacao") && action.getFunctor().equals("ligarIluminacao")){
            logger.info("atuador da Iluminacao vai acionar sonoff...");
            //chamada do sonoff para ligar Luz
            Comunicacao.Sonoff(endereco_iluminacao, "on", 0, null);
            try {
                addPercept(ASSyntax.parseLiteral("luz(ligada)"));
            } catch (ParseException e) {
                e.printStackTrace();
            }                           
        }        
        else logger.info("tentando executar : "+action+", mas ainda não implementado!");        
        
        try {
            Thread.sleep(5000);
            removePercept(ASSyntax.parseLiteral("som(gritar)"));
        } catch (InterruptedException e)  {
            e.printStackTrace();
        }  catch (ParseException e) {
			e.printStackTrace();
        }
        return true; // the action was executed with success
    }
    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}
