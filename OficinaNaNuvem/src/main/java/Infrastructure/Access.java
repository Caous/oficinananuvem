package Infrastructure;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public abstract class Access {

    public static boolean Login(List<Cookie> _cookie) {
        boolean logado = false;

        if (_cookie != null) {
            for (Cookie atual : _cookie) {
                if (atual.getName().equals("ID_USUARIO")) {
                    if (atual.getValue() != null && !atual.getValue().equals("")) {
                        logado = true;
                    }
                }

            }
        }
        return logado;
    }

    public static boolean LoginCliente(List<Cookie> _cookie) {
        boolean logado = false;

        if (_cookie != null) {
            for (Cookie atual : _cookie) {
                if (atual.getName().equals("ID_CLIENTE")) {
                    if (atual.getValue() != null && !atual.getValue().equals("")) {
                        logado = true;
                    }
                }

            }
        }
        return logado;
    }

    public static void Logout(List<Cookie> _cookie, HttpServletResponse response) {
        if (_cookie != null) {
            for (Cookie atual : _cookie) {
                if (atual.getName().equals("ID_USUARIO")) {
                    if (atual.getValue() != null) {
                        atual.setValue(null);
                        atual.setMaxAge(0);
                        response.addCookie(atual);
                    }
                }

            }
        }
    }

    public static void LogoutCliente(List<Cookie> _cookie, HttpServletResponse response) {
        if (_cookie != null) {
            for (Cookie atual : _cookie) {
                if (atual.getName().equals("ID_CLIENTE")) {
                    if (atual.getValue() != null) {
                        atual.setValue(null);
                        atual.setMaxAge(0);
                        response.addCookie(atual);
                    }
                }

            }
        }
    }

    public static int Id_USUARIO(List<Cookie> _cookie) {
        int id_usuario = 0;

        if (_cookie != null) {
            for (Cookie atual : _cookie) {
                if (atual.getName().equals("ID_USUARIO")) {
                    if (atual.getValue() != null && !atual.getValue().equals("")) {
                        id_usuario = Integer.parseInt(atual.getValue());
                    }
                }

            }
        }
        return id_usuario;
    }

    public static int Id_USUARIO_CLIENTE(List<Cookie> _cookie) {
        int id_usuario = 0;

        if (_cookie != null) {
            for (Cookie atual : _cookie) {
                if (atual.getName().equals("ID_CLIENTE")) {
                    if (atual.getValue() != null && !atual.getValue().equals("")) {
                        id_usuario = Integer.parseInt(atual.getValue());
                    }
                }

            }
        }
        return id_usuario;
    }

    public static int Id_Pefil(List<Cookie> _cookie) {
        int id_perfil = 0;

        if (_cookie != null) {
            for (Cookie atual : _cookie) {
                if (atual.getName().equals("Perfil")) {
                    if (atual.getValue() != null && !atual.getValue().equals("")) {
                        id_perfil = Integer.parseInt(atual.getValue());
                    }
                }

            }
        }
        return id_perfil;
    }

    public static String CarrinhoIdProduto(List<Cookie> _cookie) {
        String carrinho = "";

        if (_cookie != null) {
            for (Cookie atual : _cookie) {
                if (atual.getName().equals("CarrinhoProduto")) {
                    if (atual.getValue() != null && !atual.getValue().equals("")) {
                        carrinho = atual.getValue();
                    }
                }

            }
        }
        return carrinho;
    }

    public static int qtdCarrinho(List<Cookie> _cookie) {
        int  qtdcarrinho = 0;

        if (_cookie != null) {
            for (Cookie atual : _cookie) {
                if (atual.getName().equals("CarrinhoProduto")) {
                    if (atual.getValue() != null && !atual.getValue().equals("")) {
                        String carrinho = atual.getValue();
                        String carrinhoSeparador[] = carrinho.split("&");
                        qtdcarrinho = carrinhoSeparador.length;
                    }
                }

            }
        }
        return qtdcarrinho;
    }
    
    public static int limparCarrinho(List<Cookie> _cookie, HttpServletResponse response) {
        int  qtdcarrinho = 0;

        if (_cookie != null) {
            for (Cookie atual : _cookie) {
                if (atual.getName().equals("CarrinhoProduto")) {
                    if (atual.getValue() != null && !atual.getValue().equals("")) {
                        atual.setValue(null);
                        atual.setMaxAge(0);
                        response.addCookie(atual);
                    }
                }

            }
        }
        return qtdcarrinho;
    }
}
