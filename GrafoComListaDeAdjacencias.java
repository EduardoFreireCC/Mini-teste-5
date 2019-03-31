package miniTest;

import java.util.ArrayList;
import java.util.List;

public class GrafoComListaDeAdjacencias implements Grafo {

	private List<Vertice> vertices = new ArrayList<>();
	private List<VerticeInterno> verticesInternos = new ArrayList<>();

	public GrafoComListaDeAdjacencias(String... nomesVertices) {
		for (String nomeVertice : nomesVertices) {
			this.addVertice(nomeVertice);
		}
	}

	@Override
	public Vertice addVertice(String nome) {
		Vertice vertice = new Vertice();
		vertice.setNome(nome);
		vertices.add(vertice);

		VerticeInterno verticeInterno = new VerticeInterno();
		verticeInterno.setVertice(vertice);
		verticesInternos.add(verticeInterno);

		return vertice;
	}

	public Vertice getVertice(String nome) {
		for (Vertice vertice : vertices) {
			if (vertice.getNome().equals(nome)) {
				return vertice;
			}
		}

		return null;
	}

	private VerticeInterno getVerticeInterno(Vertice v) {
		for (VerticeInterno verticeInterno : verticesInternos) {
			if (verticeInterno.getVertice().equals(v)) {
				return verticeInterno;
			}
		}

		throw new RuntimeException("Vertice interno n�o encontrado");
	}

	@Override
	public void addAresta(Vertice v1, Vertice v2) {
		Aresta ida = new Aresta();
		ida.setOrigem(v1);
		ida.setDestino(v2);
		ida.setSegue(true);
		VerticeInterno verticeInternoV1 = getVerticeInterno(v1);
		verticeInternoV1.getArestas().add(ida);

		Aresta volta = new Aresta();
		volta.setOrigem(v2);
		volta.setDestino(v1);
		volta.setSegue(true);
		VerticeInterno verticeInternoV2 = getVerticeInterno(v2);
		verticeInternoV2.getArestas().add(volta);

	}

	@Override
	public List<Vertice> getVertices() {
		return vertices;
	}

	@Override
	public boolean segue(Vertice v1, Vertice v2) {
		VerticeInterno verticeInternoV1 = getVerticeInterno(v1);

		for (Aresta aresta : verticeInternoV1.getArestas()) {
			if (aresta.getDestino().equals(v2)) {
				return aresta.isSegue();
			}
		}

		return false;
	}

}

class VerticeInterno {
	private Vertice vertice;
	private List<Aresta> arestas = new ArrayList<>();

	public Vertice getVertice() {
		return vertice;
	}

	public void setVertice(Vertice vertice) {
		this.vertice = vertice;
	}

	public List<Aresta> getArestas() {
		return arestas;
	}
}