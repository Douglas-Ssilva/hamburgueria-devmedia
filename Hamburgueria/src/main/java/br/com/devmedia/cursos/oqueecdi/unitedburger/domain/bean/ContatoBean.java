package br.com.devmedia.cursos.oqueecdi.unitedburger.domain.bean;

import br.com.devmedia.cursos.oqueecdi.unitedburger.domain.model.Contato;
import br.com.devmedia.cursos.oqueecdi.unitedburger.domain.model.Notificacao;
import br.com.devmedia.cursos.oqueecdi.unitedburger.service.ContatoService;
import br.com.devmedia.cursos.oqueecdi.unitedburger.service.NotificacaoService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ContatoBean {

    @Inject
    private Contato contato;

    @Inject
    private ContatoService contatoService;

    @Inject
    private Notificacao notificacao;

    @Inject
    private NotificacaoService notificacaoService;

    private boolean receberNotificacao;

    @PostConstruct
    public void init() {
        receberNotificacao = false;
        System.out.println(receberNotificacao);
    }

    public Contato getContato() {
        return contato;
    }

    public boolean getReceberNotificacao() {
        return receberNotificacao;
    }

    public void setReceberNotificacao(boolean receberNotificacao) {
        this.receberNotificacao = receberNotificacao;
    }

    public void cadastrar(ActionEvent event) {
        FacesMessage message;

        try {
            contatoService.cadastrar(contato);

            if (receberNotificacao) {
                notificacao.setEmail(contato.getEmail());
                notificacaoService.cadastrar(notificacao);
            }

            contato = null;

            message = new FacesMessage("E-mail cadastrado com sucesso!");
        } catch (RuntimeException e) {
            message = new FacesMessage("Serviço temporariamente indisponível");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
