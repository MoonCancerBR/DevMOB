Projeto "Metafit"
Descriçao: 
Um simples sistema de entregas.
________________________________
O usuario abre a aplicação no MENU apos exibiçao de 
Existem 4 botoes.
O primeiro e para gerar o pedido de entrega.
O segundo e para ver os produtos ofertados.
O terceiro e para ter acesso ao Menu Administrativo
O quarto redireciona o usuario pra uma página generica de regulamentos da aplcaçao.
No topo ha dois botoes menores para ligar ou mandar mensagem para o "suporte tecnico". 
--Gerando o Produto:
O usuario mostra a localização atual para aplicaçao
Insere dados comoo telefone, boleto de pagamento e codigo de pedido. 
->Esses dados sao enviados ao Firebase
--Realizando o Login
O usuario realiza o login com suas autentificaçoes.
Caso tenha esquecido a senha, o usuario e direcionado a uma pagina onde e solicitado seu email de cadastro. 
E enviado uma mensagem de alteracao de senha na caixa de entrada do email.
Caso o usuario não tenha autenticaçoes, ele é direcionado a outra pagina para se cadastrar
Sera solicitado um nome, email valido,uma senha forte e a confirmaçao da senha ao digita novamente, apos isso ele tem acesso aos recursos adm.
--MenuADM
Existem 4 botoes.
O primeiro e para eliminar entregas solicitadas pelos clientes. 
->Ao selecionar, a entrega é deletada no Firebase.
O segundo e Registrar Produtos. Basicamente ao usuario é solicitado o uso da camera, ou da galeria de imagens do dispositivo. 
Ao selecionar a imagem, para salvar é necessario dar um nome. 
->A imagem é salva no Storage e sua Url é vinculada a um Produto de mesmo nome que é salvo no Firebase.
O terceiro botão é para remover produtos, sera mostado uma lista de nomes de produtos salvos no firebase, ao selecionar algum é apresentado uma preview da imagem.
->Ao excluir o produto será apagado do Firebase e a imagem vinculado a ele no Storage é deletada tambem.
O quarto botão é o que dar acesso ao Maps.
Nele sera exibido por marcadores os locais onde foram registrados os pedidos pelos clientes.
________________________________________________________________________________________________________________________________________________________________________
Questoes a resolver:
-Navegabilidade e acesso:
1. Questoes de navegabilidade usuario deve realizar o registro sempre que for acessar o MenuADM, mesmo que ja tenha logado. (Como resolver?)
2. Aviso de permissoes, pode estar faltando algum, como uso da camera por exemplo.
3. Questoes de acessibilidade, qualquer um pode criar um cadastro aleatóriamente e ter acesso Administrativos. (Como resolver?)
4. Outras questoes a definir....
