package br.ufop.trabalho.entities;

/**
 * @author Artur Guerra
 */
public class Filme {

    private String nome;
    private Data anoDeLancamento;
    private String genero;
    private int quantDvd, quantBlueRay;

    /**
     * @author Artur Guerra
     */
    public Filme(String nome, Data anoDeLancamento, String genero, int quantDvd, int quantBlueRay) {
        setNome(nome);
        setAnoDeLancamento(anoDeLancamento);
        setGenero(genero);
        setQuantDvd(quantDvd);
        setQuantBlueRay(quantBlueRay);
    }

    public String getNome() {
        return nome;
    }

    /**
     * @author Artur Guerra
     */
    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome do filme não pode ser vazio.");
        }
        this.nome = nome;
    }

    public Data getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public void setAnoDeLancamento(Data anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    public String getGenero() {
        return genero;
    }

    /**
     * @author Artur Guerra
     */
    public void setGenero(String genero) {
        if (genero == null || genero.isEmpty()) {
            throw new IllegalArgumentException("Gênero do filme não pode ser vazio.");
        }
        this.genero = genero;
    }

    public int getQuantDvd() {
        return quantDvd;
    }

    /**
     * @author Artur Guerra
     */
    public void addQuantDvd(int quantDvd) {
        if (quantDvd < 0) {
            throw new IllegalArgumentException("A quantidade de DVDs a adicionar não pode ser negativa.");
        }
        this.quantDvd += quantDvd;
    }

    /**
     * @author Artur Guerra
     */
    public void removeQuantDvd(int quantDvd) {
        if (this.quantDvd - quantDvd < 0) {
            throw new IllegalArgumentException("A quantidade de DVDs a remover é maior que o disponível.");
        }
        this.quantDvd -= quantDvd;
    }

    /**
     * @author Artur Guerra
     */
    public void setQuantDvd(int quantDvd) {
        if (quantDvd < 0) {
            throw new IllegalArgumentException("Quantidade de DVDs não pode ser negativa.");
        }
        this.quantDvd = quantDvd;
    }

    public int getQuantBlueRay() {
        return quantBlueRay;
    }

    /**
     * @author Artur Guerra
     */
    public void addQuantBlueRay(int quantBlueRay) {
        if (quantBlueRay < 0) {
            throw new IllegalArgumentException("A quantidade de Blu-rays a adicionar não pode ser negativa.");
        }
        this.quantBlueRay += quantBlueRay;
    }

    /**
     * @author Artur Guerra
     */
    public void removeQuantBlueRay(int quantBlueRay) {
        if (this.quantBlueRay - quantBlueRay < 0) {
            throw new IllegalArgumentException("A quantidade de Blu-rays a remover é maior que o disponível.");
        }
        this.quantBlueRay -= quantBlueRay;
    }

    /**
     * @author Artur Guerra
     */
    public void setQuantBlueRay(int quantBlueRay) {
        if (quantBlueRay < 0) {
            throw new IllegalArgumentException("Quantidade de Blu-rays não pode ser negativa.");
        }
        this.quantBlueRay = quantBlueRay;
    }

    /**
     * Método para formatar o string
     *
     * @author Artur Guerra
     */
    @Override
    public String toString() {
        return String.format("%s (%d/%d/%d) - DVDs: %d, Blu-rays: %d", nome, anoDeLancamento.getDia(),
                anoDeLancamento.getMes(), anoDeLancamento.getAno(), quantDvd, quantBlueRay);
    }

    public void decrementarQtdDvd() {
        if (quantDvd > 0) {
            quantDvd--;
        }
    }

    public void decrementarQtdBlueRay() {
        if (quantBlueRay > 0) {
            quantBlueRay--;
        }
    }
}
