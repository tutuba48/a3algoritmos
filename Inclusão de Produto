            Object resp = JOptionPane.showInputDialog(
                    null,
                    texto,
                    "Pizzaria da Unisul — Cadastro de Produtos",
                    JOptionPane.PLAIN_MESSAGE,
                    IconePizza.get(),
                    null,
                    "");
            String entrada = (resp == null) ? null : resp.toString();
            if (entrada == null) {
                return;
            }

            switch (entrada.trim()) {
                case "1": incluir();          break;
                case "2": alterar();          break;
                case "3": consultar();        break;
                case "4": excluir();          break;
                case "0": continuar = false;  break;
                default:  opcaoInvalidaCadastro();
            }
        }
    }

    /** Mensagem mostrada quando o usuário digita uma opção que não existe. */
    private static void opcaoInvalidaCadastro() {
        Util.erro("Opção inválida. Digite um número entre 0 e 4.");
    }

    /**
     * Tela 1.1.1 — Inclusão de Produto.
     */
    public static void incluir() {
        boolean novaInclusao = true;
        while (novaInclusao) {
            if (Estoque.cheio()) {
                Util.erro("Estoque cheio! Capacidade máxima de "
                        + Estoque.CAPACIDADE + " produtos atingida.");
                return;
            }

            Util.mensagem("Inclusão", Util.cabecalho("INCLUSÃO DE PRODUTO"));
