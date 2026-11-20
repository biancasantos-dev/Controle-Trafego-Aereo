public class EventoLog {
    private String mensagem;

    public EventoLog(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    @Override
    public String toString() {
        return "Log: " + mensagem;
    }
}
