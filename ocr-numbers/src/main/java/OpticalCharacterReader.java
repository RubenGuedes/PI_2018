import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OpticalCharacterReader 
{

	public static final List[] IMAGENS = 
	{ 
			Arrays.asList(
							" _ ", 
							"| |", 
							"|_|", 
							"   "), 
			Arrays.asList(
							"   ", 
							"  |", 
							"  |", 
							"   "),
			Arrays.asList(
							" _ ", 
							" _|", 
							"|_ ", 
							"   "), 
			Arrays.asList(
							" _ ", 
							" _|", 
							" _|", 
							"   "),
			Arrays.asList(
							"   ", 
							"|_|", 
							"  |", 
							"   "), 
			Arrays.asList(
							" _ ", 
							"|_ ", 
							" _|", 
							"   "),
			Arrays.asList(
							" _ ", 
							"|_ ", 
							"|_|", 
							"   "), 
			Arrays.asList(
							" _ ", 
							"  |", 
							"  |", 
							"   "),
			Arrays.asList(
							" _ ", 
							"|_|", 
							"|_|", 
							"   "), 
			Arrays.asList(
							" _ ", 
							"|_|", 
							" _|", 
							"   ") 
		};

	public String parse(List<String> lines) 
	{
		int linhas = lines.size();
		int colunas = lines.get(0).length();
		// Regras
		if (linhas % 4 != 0 || colunas % 3 != 0) 
		{
			throw new IllegalArgumentException("A imagem deve ter 4 linhas e 3 colunas.");
		}

		String result = new String();
		for (int r = 0; r < linhas; r += 4) 
		{
			if (result.length() != 0) 
			{
				result += ",";
			}

			for (int c = 0; c < colunas; c += 3) 
			{
				result +=  reconhecer(lines, r, c) ;
			}
		}
		return result;
	}

	public char reconhecer(List<String> lines, int r, int c) 
	{
		List<String> part = IntStream
								.range(r, r + 4)
								.mapToObj(i -> lines.get(i).substring(c, c + 3))
								.collect(Collectors.toList());

		for (int i = 0; i < IMAGENS.length; i++) 
		{
			if ( igual(part, IMAGENS[i]) ) 
			{
				return (char) (i + '0');
			}
		}
		return '?';
	}
	public boolean igual(List<String> part, List<String> image) 
	{
		return  IntStream.range(0, part.size())
									   .allMatch(i -> part.get(i)
									   					  .equals(image.get(i))
									   					  );
	}

}