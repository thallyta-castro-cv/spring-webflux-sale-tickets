# Saletickets

Saletickets é uma API de gerenciamento de eventos e ingressos e é um microserviço reativo com Webflux que faz parte do projeto Sale Tickets que é um site de eventos onde haverá vendas de ingressos.
Esta API é desenvolvida com **Spring Boot**, permitindo criar, buscar, atualizar e excluir eventos. 
O projeto inclui testes automatizados utilizando **JUnit** e **WebTestClient** para garantir a confiabilidade da API.

## Tecnologias Utilizadas

- **Java 21**  
- **Spring Boot**  
- **Spring WebFlux** (para testes reativos)  
- **JUnit 5** (para testes unitários e de integração)  
- **WebTestClient** (para testes de API)  

## ⚙️ Como Executar o Projeto

### 1️⃣ Pré-requisitos  
- Java 21 instalado  
- Maven instalado  

### 2️⃣ Clonar o Repositório  
```sh
git clone https://github.com/seu-usuario/saletickets.git
cd saletickets
```

### 3️⃣ Construir e Executar a Aplicação
```sh
mvn clean install
mvn spring-boot:run
```

4️⃣ Executar Testes
```sh
mvn test
````

## 🔗 Endpoints da API

### 🎫 Tickets
| Método   | Endpoint                        | Descrição                                        | Produz |
|----------|---------------------------------|--------------------------------------------------|--------|
| GET      | `/tickets`                      | Obtém todos os tickets                           | `application/json` |
| GET      | `/tickets/{id}`                 | Obtém um ticket específico pelo ID              | `application/json` |
| POST     | `/tickets`                      | Cria um novo ticket                             | `application/json` |
| DELETE   | `/tickets/{id}`                 | Deleta um ticket pelo ID                        | - |
| PUT      | `/tickets/{id}`                 | Atualiza um ticket pelo ID                      | `application/json` |
| POST     | `/tickets/purchase`             | Realiza a compra de um ticket                   | `application/json` |
| GET      | `/tickets/{id}/available`       | Obtém a disponibilidade do ticket em tempo real | `text/event-stream` |

### 🎟️ Eventos
| Método   | Endpoint                         | Descrição                                           | Produz |
|----------|----------------------------------|-----------------------------------------------------|--------|
| GET      | `/events`                        | Obtém todos os eventos                             | `text/event-stream` |
| GET      | `/events/{id}`                   | Obtém um evento específico pelo ID                | `application/json` |
| GET      | `/events/category/{type}`        | Obtém eventos por categoria                       | `text/event-stream` |
| GET      | `/events/{id}/translate/{language}` | Obtém a tradução de um evento para um idioma específico | `application/json` |
| POST     | `/events`                        | Registra um novo evento                           | `application/json` |
| DELETE   | `/events/{id}`                   | Deleta um evento pelo ID                          | - |
| PUT      | `/events/{id}`                   | Atualiza um evento pelo ID                        | `application/json` |

### Integração com API Externa de Tradução (DeepL)

Este projeto contém integração com a API de tradução do **DeepL**, um serviço de tradução automática que utiliza inteligência artificial para fornecer traduções de alta qualidade. O DeepL é amplamente reconhecido pela precisão e fluência de suas traduções, sendo uma das melhores opções para tradução de textos de forma eficiente.

#### Sobre a API do DeepL

A API do DeepL oferece acesso aos serviços de tradução e também permite o uso de dicionários, sugestões de traduções e mais, em vários idiomas. Ela suporta tradução entre diversos pares de idiomas e pode ser facilmente integrada a sistemas web para tradução automática em tempo real.

A API do DeepL fornece as seguintes funcionalidades:
- Tradução de texto em mais de 25 idiomas.
- Detecção automática do idioma de entrada.
- Traduções alternativas para melhorar a qualidade e fluência.
- Suporte para traduções de documentos (como arquivos .docx, .pptx e outros formatos).
- Limitações baseadas no plano de assinatura.

#### Como Obter uma Chave de API do DeepL

Para integrar sua aplicação com o DeepL e começar a fazer chamadas para a API, é necessário obter uma chave de API. Abaixo estão os passos para isso:

1. **Acesse o site do DeepL:**
   Vá até [DeepL API](https://www.deepl.com/pro) para acessar as opções de planos e obter sua chave de API.

2. **Crie uma Conta ou Faça Login:**
   Caso ainda não tenha uma conta, crie uma na plataforma do DeepL. Se já tiver uma conta, basta fazer login.

3. **Escolha um Plano:**
   O DeepL oferece planos pagos e gratuitos. O plano gratuito tem limitações no número de caracteres traduzidos, enquanto os planos pagos oferecem mais recursos e maior quantidade de caracteres. Escolha o plano que melhor se adapta às suas necessidades.

4. **Obtenha a Chave de API:**
   Após escolher o plano, você será direcionado para uma página onde poderá gerar sua chave de API. Essa chave será usada para autenticar suas solicitações à API.

5. **Configure a Chave de API em Seu Projeto:**
   Após obter a chave, insira-a no seu código ou configure-a no ambiente da sua aplicação para começar a fazer chamadas para a API de tradução.

Lembre-se de que a chave de API deve ser mantida em segredo, pois ela está vinculada à sua conta e ao seu plano de uso.






