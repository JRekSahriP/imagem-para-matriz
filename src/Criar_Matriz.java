import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Criar_Matriz{
    static int[][] matriz;
	static int pixels_Width, pixels_Height;

	public static void main(String[] args) {
		Frame janela = new Frame();
		FileDialog arquivo = new FileDialog(janela, "Selecionar arquivo", FileDialog.LOAD);
		
		arquivo.setVisible(true);
		
		String local = arquivo.getDirectory();
		String pasta = arquivo.getFile();
		
		if (pasta == null && local == null) {
			System.out.print("nenhum arquivo selecionado");
			System.exit(0);
		}
		BufferedImage imagem = carregar(local+pasta);
		
		pixels_Width = imagem.getWidth();
		pixels_Height = imagem.getHeight();
		
		preenchermatriz(imagem);
		
		print_matriz();
		
		janela.dispose();
	}
    public static void preenchermatriz(BufferedImage imagem) {
        matriz = new int[pixels_Height][pixels_Width];
            for (int y = 0; y<matriz.length; y++) {
                for (int x = 0; x< matriz[0].length;x++) {
                	int rgb = imagem.getRGB(x, y);
                	// Verifica se o pixel é transparente ou branco e o converte para 0 ou 1, respectivamente.
                	matriz[y][x] = (rgb == 0 || rgb == -1) ? 0 : 1;
            }
        }
    }
    public static void print_matriz() {
        System.out.println("int[][] name = {");
        for (int i = 0; i < matriz.length; i++) {
            System.out.print("{");
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j]);
                if (j < matriz[0].length - 1) {System.out.print(",");}
                
            }System.out.println("},");
            
        }System.out.println("};");
    }
    public static BufferedImage carregar (String link) throws IllegalArgumentException {
    	try {
    		return ImageIO.read(new File(link));
    	} catch(IOException e) {
    		throw new IllegalArgumentException("Não foi possível ler o arquivo" + link);
    	}
    }
}