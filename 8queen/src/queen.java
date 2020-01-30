import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class queen extends java.applet.Applet implements MouseListener {
	int size;
	int ban[][];

	public void init() {
		int i, j;

		addMouseListener(this);

		Dimension d = getSize();
		size = (Math.min(d.width, d.height) - 1) / 8;

		ban = new int[8][8];

		for (i=0; i<8; i++) {
			for (j=0; j<8; j++) {
				ban[i][j] = 0;
			}
		}

		setBackground(Color.black);
	}

	public void mouseClicked(MouseEvent me) {
		int x, y, i, j;

		x = me.getX() / size;
		y = me.getY() / size;
		if ((x < 0) || (x > 7) || (y < 0) || (y > 7)) return;
		if (ban[x][y] == -1) return;
		ban[x][y] = 1 - ban[x][y];

		for (i=0; i<8; i++) {
			for (j=0; j<8; j++) {
				if (ban[i][j] != 1) {
					if (!canPlace(i, j)) ban[i][j] = -1;
					else ban[i][j] = 0;
				}
			}
		}

		repaint();
	}

	public void mouseEntered(MouseEvent me) {
	}
	public void mouseExited(MouseEvent me) {
	}
	public void mousePressed(MouseEvent me) {
	}
	public void mouseReleased(MouseEvent me) {
	}

	public void paint(Graphics g) {
		int i, j;

		g.setColor(Color.blue);
		for (i=0; i<=8; i++) {
			g.drawLine(0, i * size, 8 * size, i * size);
			g.drawLine(i * size, 0, i * size, 8 * size);
		}

		for (i=0; i<8; i++) {
			for (j=0; j<8; j++) {
				if ((ban[i][j] == 1) || ban[i][j] == -1) {
					g.setColor(Color.lightGray);
					g.fillRect(i*size+1, j*size+1, size-1, size-1);
				}
				if (ban[i][j] == 1) {
					g.setColor(Color.white);
					g.fillOval(i*size+size/4, j*size+size/4, size/2, size/2);
				}
			}
		}
	}

	boolean canPlace(int x, int y) {
		int i, tmp;

		for (i=0; i<8; i++) {
			if (ban[i][y] == 1) return false;
			if (ban[x][i] == 1) return false;
			tmp = y+x-i;
			if ((tmp >=0) && (tmp <=7)) {
				if (ban[i][tmp] == 1) return false;
			}
			tmp = y-x+i;
			if ((tmp >=0) && (tmp <=7)) {
				if (ban[i][tmp] == 1) return false;
			}
		}

		return true;
	}
}