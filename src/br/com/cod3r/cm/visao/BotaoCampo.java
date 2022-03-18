package br.com.cod3r.cm.visao;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.event.MouseInputListener;

import br.com.cod3r.cm.modelo.Campo;
import br.com.cod3r.cm.modelo.CampoEvento;
import br.com.cod3r.cm.modelo.CampoObservador;

@SuppressWarnings("serial")
public class BotaoCampo extends JButton implements CampoObservador, MouseInputListener {

	private final Color BG_PADRAO = new Color(184,184,184);
	private final Color BG_MARCADO = new Color(8,179,247);
	private final Color BG_EXPLOSAO = new Color(189,66,68);
	private final Color TEXTO_VERDE = new Color(0,100,0);
	
	private Campo campo;
	
	public BotaoCampo(Campo campo) {
		this.campo = campo;
		setBackground(BG_PADRAO);
		setBorder(BorderFactory.createBevelBorder(0));
		
		addMouseListener(this);
		campo.registrarObservador(this);
	}

	@Override
	public void eventoOcorreu(Campo campo, CampoEvento evento) {
		switch(evento) {
		case ABRIR:
			aplicarEstiloAbrir();
			break;
		case MARCAR:
			aplicarEstiloMarcar();
			break;
		case EXPLODIR:
			aplicarEstiloExplodir();
			break;
		default:
			aplicarEstiloPadrao();
		}
		
		
	}
	
	private void aplicarEstiloPadrao() {
		setBackground(BG_PADRAO);
		setBorder(BorderFactory.createBevelBorder(0));
		setText("");
		
	}

	private void aplicarEstiloExplodir() {
		
		setBackground(BG_EXPLOSAO);
		setForeground(Color.WHITE);
		setText("X");
	}

	private void aplicarEstiloMarcar() {
		setBackground(BG_MARCADO);
		setForeground(Color.BLACK);
		setText("M");
		
	}

	private void aplicarEstiloAbrir() {
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		if(campo.isMinado()) {
			setBackground(BG_EXPLOSAO);
			return;
		}
		
		setBackground(BG_PADRAO);
		
		
		switch (campo.minasNaVizinhanca()) {
		case 1: 
			setForeground(TEXTO_VERDE);
			break;
		case 2:
			setForeground(Color.BLUE);
		case 3:
			setForeground(Color.YELLOW);
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
			setForeground(Color.RED);
			break;
		
		default:
			setForeground(Color.PINK);
		}
		String valor = !campo.vizinhancaSegura() ? campo.minasNaVizinhanca() + "":"";
		setText(valor);
	}
	//INTERFACE DE EVENTOS DO MOUSE

	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1) {
			campo.abrir();
		} else {
			campo.alternarMarcacao();
		}
		
	}


	public void mouseReleased(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mouseDragged(MouseEvent e) {}

	public void mouseMoved(MouseEvent e) {}
	
	
}
