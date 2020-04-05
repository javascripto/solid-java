# S.O.L.I.D.

## Coesão e o (S) Single Responsibility Principle

Olá! Bem-vindo ao curso de Orientação a objetos avançado e SOLID do Alura. Neste curso eu vou discutir com vocês todas aquelas ideias de Orientação a objetos, como coesão, acoplamento, encapsulamento etc., só que dessa vez com um ponto de vista um pouquinho mais avançado, um pouquinho mais profundo nesses assuntos.

Vou discutir com vocês como conseguir fazer classes que são bastante coesas, que são pouco acopladas, como evitar fazer classes e métodos que não estão bem encapsulados etc. e tal. Pra esse curso, eu espero que você conheça um pouquinho de Orientação a objetos, já tenha alguma prática com alguma linguagem que dá suporte. Aqui no curso, vou usar Java, mas tudo o que eu discutir vai funcionar para todas as linguagens que são OO. Tá legal?

A primeira aula vai ser sobre coesão. Todo mundo já ouviu falar de coesão, certo? E tem aquela máxima lá, “Olha, todas as suas classes, elas têm que ser sempre muito coesas.” Agora a pergunta é: como fazer isso? Como criar classes que são coesas o tempo inteiro? Como evitar criar classes que tenham baixa coesão?

E para discutir isso, eu vou mostrar um código que parece código do mundo real. Dá uma olhada. Eu tenho aqui a minha classe CalculadoraDeSalario. Essa classe, dá para ver pelo método calcula, tem por objetivo calcular o salário de um funcionário. Dado um funcionário, e um cargo que esse funcionário tem, eu tenho uma regra diferente do cálculo do salário dele. Dá uma olhada:

```java
public class CalculadoraDeSalario {


    public double calcula(Funcionario funcionario) {
        if(DESENVOLVEDOR.equals(funcionario.getCargo())) {
            return dezOuVintePorcento(funcionario);
        }

        if(DBA.equals(funcionario.getCargo()) || TESTER.equals(funcionario.getCargo())) {
            return quinzeOuVinteCincoPorcento(funcionario);
        }

        throw new RuntimeException("funcionario invalido");
    }

    private double dezOuVintePorcento(Funcionario funcionario) {
        if(funcionario.getSalarioBase() > 3000.0) {
            return funcionario.getSalarioBase() * 0.8;
        }
        else {
            return funcionario.getSalarioBase() * 0.9;
        }
    }

    private double quinzeOuVinteCincoPorcento(Funcionario funcionario) {
        if(funcionario.getSalarioBase() > 2000.0) {
            return funcionario.getSalarioBase() * 0.75;
        }
        else {
            return funcionario.getSalarioBase() * 0.85;
        }
    }

}
```

Se o cara for desenvolvedor, ele cai nesse método aí 10% ou 20%. Esse método lá dentro verifica se o salário dele é maior do que tanto, e aí ele dá um desconto. Caso contrário (else), ele dá um outro desconto. A mesma coisa acontece para DBA e TESTER. Veja que ambos compartilham a mesma regra, essa regra de 15% ou 25%. E ali, a implementação é mais ou menos a mesma.

Essa é uma classe que parece bastante com código do mundo real. Certo? Onde eu tenho classes que olham o estado de um objeto e, a partir daí, decidem qual algoritmo executar, e fazem isso por meio de ifs e fors e whiles etc. e tal.

Agora a pergunta é: qual é o problema desse código? Será que ele é um código coeso? Será que ele está muito acoplado? Vamos lá.

Voltando aqui para ele. Esse código, ele não é lá muito coeso, está certo? Como que eu sei disso? A minha primeira dica é o seguinte, eu sempre paro e penso: “Puxa, será que essa classe vai parar de crescer? Porque veja só: se a classe é coesa, ou seja, ela tem uma única responsabilidade, ela cuida de apenas uma única parte do meu sistema, ela representa uma única entidade. Se essa classe é coesa, ela tem que parar de crescer um dia. Porque as responsabilidades de uma entidade um dia param de aparecer.

Nesse caso, em particular, não. Veja só. Sempre que eu criar um cargo novo no meu sistema, eu vou ter que alterar essa classe. Sempre, sempre, sempre. Agora imagina no mundo real, muito mais complexo do que esse código, onde eu tenho, sei lá, 30, 40, 50 cargos. E imagina que eu tenha 30 regras diferentes. O que vai acontecer com essa classe? Essa classe vai ficar gigante, ela vai ter 30 regras diferentes. Tá certo? Então, ela não vai ser nada coesa.

Qual é a consequência disso? Um código complicado. Todo mundo sabe a consequência de um código complicado, certo? É mais difícil de manter, é muito mais suscetível a um bug, porque o código é enorme, você vai deixar passar alguma coisa ali, o reuso é muito menor, porque se a classe faz muita coisa é difícil levá-la pra um outro sistema. Porque o outro sistema raramente vai precisar de tudo o que ela faz. E assim por diante.

Veja só, pessoal, que complexidade, um código complicado, ele é bastante discutível no mundo OO. Porque às vezes eu posso ter um código complicado, mas dentro de uma classe coesa. E esse tipo de código me incomoda menos. Porque se eu tenho um método público, cujo código está feio, é fácil: eu jogo a implementação dele fora e escrevo de novo. Tem um monte de técnica de refatoração pra isso. Escrever variáveis com bons nomes, criar métodos privados, extrair para variáveis locais que explicam melhor o que o algoritmo está fazendo etc. e tal. Eu nem vou entrar nesse mérito de refatoração, porque é uma outra discussão e você pode até fazer o nosso curso sobre o assunto (o curso de refatoração).

Mas aqui, o que me incomoda é que essa classe, além de complicada, ela não é coesa. Ela vai crescer pra sempre. Então, ela vai dificultar e muito a vida do desenvolvedor.

Veja o sistema hoje. Eu tenho uma CalculadoraDeSalario, que tem as várias regras de cálculo. E toda vez que surgir uma regra nova, eu vou ter que enfiá-la aí dentro. Esse tipo de código, galera, ainda dificulta a testabilidade. Se eu estou pensando em teste automatizado, o teste dessa classe vai ser muito complicado. Eu vou ter que escrever uma bateria imensa pra ela. Difícil, certo? Qual é a ideia?

O que eu estou tentando fazer agora é pegar cada uma das regras que eu tinha de cálculo ? DezOuVintePorCento, QuinzeOuVintePorCento, TrintaOuQuarentaPorCento, sei lá, as regras que forem aparecendo -, e colocar cada uma dessas regras num único lugar.

Veja só o que ia acontecer na prática. Vou voltar aqui pro meu código. Eu tenho os dois métodos privados, e imagina que cada um desses métodos privados eu vá levar para uma classe em particular. Eu vou extrair um método privado e colocar numa classe. O que eu vou ganhar? Veja só que cada uma dessas regras, cada uma dessas classes, será coesa. Porque eu vou ter uma classe cuja única responsabilidade é entender da regra de DezOuVintePorCento, a outra, de QuinzeOuVinteCincoPorCento.

Ou seja, cada classe vai entender de uma única regra. Se a regra mudar, tudo bem, eu vou lá e mexo na classe. Mas veja só que ela vai mudar por um único motivo. O motivo é se essa regra em particular mudar. Se uma regra nova aparecer, eu não vou nem precisar abrir a classe DezOuQuinzePorCento ou QuinzeOuVinteCincoPorCento, não vou. Vou simplesmente criar uma nova classe.

Aqui estou criando classes coesas. E como eu optei por tratar a coesão nesse código? Eu, com a minha experiência, percebi que o que muda de um pro outro é basicamente a regra que é aplicada em cada um dos casos. Tá certo?

Eu peguei a regra e coloquei em classes menores. Essas classes serão mais coesas. Excelente. Eu vou mostrar isso em código agora pra vocês, fazendo essa refatoração, pra vocês verem como isso vai ficar muito mais legal.

Agora no Eclipse, vamos lá. A primeira coisa que eu vou fazer é pegar cada um desses métodos privados e extrair pra uma classe. Então veja, vou criar a classe que eu vou chamar de DezOuVintePorCento.

E vou pegar esse método privado aqui:

```java
class CalculadoraDeSalario {
    private double dezOuVintePorcento(Funcionario funcionario) {
        if(funcionario.getSalarioBase() > 3000.0) {
            return funcionario.getSalarioBase() * 0.8;
        }
        else {
            return funcionario.getSalarioBase() * 0.9;
        }
    }
}
```


E vou jogar pra lá. Por enquanto deixa assim, está meio estranho mas tudo bem. Vou fazer a mesma coisa com o QuinzeOuVinteECincoPorCento. Nova classe:

```java
class CalculadoraDeSalario {
   private double quinzeOuVinteCincoPorcento(Funcionario funcionario) {
        if(funcionario.getSalarioBase() > 2000.0) {
            return funcionario.getSalarioBase() * 0.75;
        }
        else {
            return funcionario.getSalarioBase() * 0.85;
        }
    }
}
```

Legal. A primeira coisa que eu já percebi. É que veja só que tem duas coisas em comum entre essas duas classes que eu acabei de criar, certo? Ambas devolvem um double e recebem um Funcionario, e ambas são uma regra de cálculo. No mundo orientado a objetos, a gente vai criar interfaces, certo? É sempre legal programar pra interfaces.

No próximo capítulo sobre acoplamento, eu vou discutir a importância de interfaces. Elas são estáveis, etc. e tal. Mas eu chego lá.

Aqui por enquanto eu vou só criar a interface, então uma RegraDeCalculo, método public double calcula que vai receber um funcionário aqui, que eu vou colocar funcionario:

public double calcula(Funcionario funcionario);
Agora eu vou nessas duas regras de negócio que eu tenho, e vou implementar, implements RegraDeCalculo. Vou mudar o método aqui para calcula, o método fica public, certo? Legal.

```java
public class DezOuVintePorCento implements RegraDeCalculo {

    public double calcula(Funcionario funcionário) {
            if(funcionario.getSalarioBase() > 3000.0) {
                return funcionario.getSalarioBase() * 0.8;
        }
        else {
            return funcionario.getSalarioBase() * 0.9;
        }
 }
}
```

A mesma coisa no QuinzeOuVinteECincoPorCento:
```java
public class QuinzeOuVinteECincoPorCento implements RegraDeCalculo  {
    public double calcula(Funcionario funcionario) {
        if(funcionario.getSalarioBase() > 2000.0) {
            return funcionario.getSalarioBase() * 0.75;
        }
        else {
            return funcionario.getSalarioBase() * 0.85;
        }
    }

}
```
Excelente. Veja só. As duas classes que eu criei com as regras de negócio são muito mais coesas. Essa aqui só tem a regra de QuinzeOuVinteECincoPorCento, e essa aqui só tem a de DezOuVintePorCento. Ótimo!

Veja só que essa classe – as duas, na verdade – não sofrem o problema da classe antiga da CalculadoraDeSalario, porque essa classe não vai crescer pra sempre. A única responsabilidade dela é cuidar dessa regra de DezOuVintePorCento, e dessa outra, de QuinzeOuVinteECincoPorCento, e assim por diante.

Agora vou voltar aqui para a CalculadoraDeSalario e vamos fazer a refatoração mais simples, que é dar um new aqui na regra. DezOuVintePorCento(). calcula e passo o funcionario. A mesma coisa aqui no debaixo, return new QuinzeOuVinteECincoPorCento().calcula(funcionario);:

```java
public class CalculadoraDeSalario {


    public double calcula(Funcionario funcionario) {
        if(DESENVOLVEDOR.equals(funcionario.getCargo())) {
            return new DezOuVintePorCento(). calcula(funcionario);
        }

        if(DBA.equals(funcionario.getCargo()) || TESTER.equals(funcionario.getCargo())) {
            return new QuinzeOuVinteECincoPorCento().calcula(funcionario);
        }

        throw new RuntimeException("funcionario invalido");
    }
}
```
Ótimo, já está melhor, certo? Esse nosso código já está bem melhor. Todas as classes são mais ou menos coesas, mais ou menos por que essas duas estão bem coesas, mas a CalculadoraDeSalario está meio estranha ainda. Essa aqui ainda não para de crescer, certo, sempre que um cargo novo aparecer, eu vou ter que lembrar de colocar um if a mais aqui.

Vamos resolver isso aqui. Como que eu vou fazer? Fácil. Todo cargo tem uma regra de negócio associada, certo? Então é isso que eu vou fazer aqui. Quando eu criar um cargo, eu vou passar pra ele o tipo de regra de negócio que ele vai usar. DESENVOLVEDOR(new DezOuVintePorCento()),, o DBA(new QuinzeOuVinteECincoPorCento()),, e a mesma coisa para o meu TESTER(new QuinzeOuVinteECincoPorCento());:

```java
public enum Cargo {
    DESENVOLVEDOR(new DezOuVintePorCento()),
    DBA(new DezOuVintePorCento()),
    TESTER(new QuinzeOuVinteECincoPorCento());
}
```

Está certo? As pessoas esquecem – esse é um detalhe do Java – que o enum é quase uma classe, eu posso ter código nele.

O que eu vou fazer aqui, vou criar o construtor do Cargo, que vai receber uma RegraDeCalculo, que vou chamar de regra, vou guardar essa regra num atributo do enum, e vou criar aqui o getRegra:

```java
public enum Cargo {
    // ...

    private RegraDeCalculo regra;
    
    Cargo(RegraDeCalculo regra)  {
        this.regra = regra;
    }
    public RegraDeCalculo getRegra()  {
        return regra;
    }
}
```
Por que que eu fiz isso? Porque, veja só, com o enum desse jeito, se eu criar um cargo novo, igual, por exemplo SECRETARIO, se eu fizer isso aqui, o código não compila. Ele vai, obrigatoriamente, me pedir o quê? Uma regra de cálculo. Está certo?

Então, vamos lá. Nosso código está funcionando e eu vou voltar aqui para a CalculadoraDeSalario. Aqui, veja só como ficou mais fácil, dá pra jogar tudo isso aqui fora e fazer simplesmente return funcionario.getCargo().getRegra().calcula(funcionario);:

```java
public double calcula(Funcionario funcionario)  {
    return funcionario.getCargo().getRegra().calcula(funcionario);`
}
```
Eu poderia até, na verdade, esconder isso aqui dentro do próprio funcionario, alguma coisa como:
```java
public double  calcula(Funcionario funcionario)  {
     return funcionario.calculaSalario();
}
```
E esse método lá dentro vai fazer simplesmente:

```java
public double calculaSalario()  {
    return  cargo.getRegra().calcula(this);
}
```
Calcula para ele mesmo (this). Tudo isso aqui continua funcionando. Apaguei os imports ali em cima e o código ficou menor.

Nessa minha implementação, talvez essa classe CalculadoraDeSalario passe até a ser inútil, porque agora ela só tem uma única linha de código, calculaSalario(). O meu grande segredo aqui foi as classes DezOuVintePorCento, QuinzeOuVinteECincoPorCento.

Dá uma olhada também na classe Funcionario. A minha classe Funcionario é bem simples, ela tem um monte de atributos, um deles é o cargo, e aqui o calculaSalario. Essa classe é coesa, até porque não tem muito por que ela não ser, certo? Ela só tem regras de Funcionario. Não tem problema.

O problema de coesão que eu dei pra vocês nesse exercício foi justamente naquela CalculadoraDeSalario que fazia muita coisa, e a solução foi espalhar as regras em classes diferentes, e fazer o Cargo ser mais inteligente. Certo?

Já vou explicar também a vantagem dessa refatoração aqui do Cargo, que não envolve só coesão. Já chego lá.

Ótimo, nosso código ficou muito melhor. Mas agora eu quero dar uma olhada no código antigo de novo, porque na nossa refatoração, nós resolvemos dois problemas, na verdade, que essa classe tinha. Um deles era de coesão, que eu discuti com vocês, mas o segundo eu passei batido, porque eu não queria entrar em detalhe agora. Mas olha só, um outro grande problema de códigos orientados a objeto é a quantidade de pontos que eu tenho que mudar, dada uma alteração do meu usuário.

Veja só. Nesse código antigo, da maneira com que estava programado antes, se eu criasse um cargo novo, por exemplo, o cargo de SECRETARIO, o que eu ia ter que fazer? Eu ia ter que mexer no meu enum, certo? Ia ter que adicionar uma linha a mais ali no meu enum, só que, mais do que isso, eu ia ter que abrir essa classe CalculadoraDeSalario e colocar a regra dele aí.

Agora, a pergunta é: e se eu esquecesse? O ponto é às vezes nem esquecer, o ponto é que às vezes eu sou um desenvolvedor que caí de paraquedas no projeto e não conheço tudo. E como essa mudança é indireta, não é clara no meu código – toda vez que um cargo novo aparece eu tenho que mexer na CalculadoraDeSalario eu não vou lembra. Esse é um grande problema de código OO. É a propagação de mudança.

Eu tenho que mexer num único ponto, idealmente. O cliente pediu uma mudança, eu vou num único lugar, e nesse lugar eu mexo, e a mudança propaga pro resto. Eu não tenho que programar usando Ctrl + F. Esse é um ótimo indício de que seu código não está bem orientado a objetos: é quando você tem que, o tempo inteiro, apelar para Ctrl + F, ou pra grep no Linux, alguma forma pra sair buscando código pra descobrir onde tem que mudar.

Idealmente, esses pontos de mudança, eles são sempre explícitos no seu design. No nosso código novo, nós resolvemos esse problema. No próprio enum agora eu tenho lá, eu passo pra ele a regra de cálculo que eu vou usar.

Esse tipo de código, galera, a gente fala que é um problema de encapsulamento. Porque o enum ali, Cargo, deixou vazar pro mundo de fora aonde vai ficar essa regra de cálculo, não estava escondida nele. O Cargo sabe a regra que tem que escolher. Então esse código tem que estar dentro dele.

Está certo? Antes, o nosso código, além de não ser coeso, ele não estava encapsulado. Mais pra frente, tem um capítulo em que eu só vou falar de encapsulamento. Mas já queria alertar pra vocês desde o começo o que é encapsulamento, e olha só como isso é perigoso. Tá bem?

De novo, encapsulamento aqui estava problemático, a regra de cálculo estava saindo da classe Cargo, do enum Cargo. Eu tinha que mexer em outro lugar para colocar a regra que é relativa ao cargo. E agora eu resolvi, certo, sempre que eu criar um enum novo, o compilador vai me encher o saco e vai pedir pra eu passar ali a regra de cálculo. Não tem como eu criar um cargo sem passar a regra de cálculo.

“Ah, mas estou criando um cargo que não tem regra”, ótimo. Você vai criar a regra e vai colocar no enum. Tudo ligadinho no meu sistema, eu não vou ter o problema de esquecer de passar uma regra pra um cargo.

Veja que esse problema, pessoal, no mundo real pode ser pior. Porque eu posso ter 10 classes diferentes que tenham uma regra de negócio relacionada ao cargo. Aqui eu fiz a brincadeira com o salário, mas eu poderia ter 3, 4, 5 outras regras espalhadas em 4, 5 outras classes diferentes. Tá bem?

Voltando aqui para o meu slide, classes coesas, eu as quero o tempo inteiro. Por quê? Porque uma classe coesa é mais fácil de ser lida, eu tenho mais reuso, eu posso pegá-la e levar para um outro sistema, ela provavelmente vai ser mais simples, porque ela vai ter menos código, e eu não vou precisar abri-la o tempo inteiro. Essa é uma coisa com a qual me preocupo bastante, uma classe coesa, ela geralmente vem fechada no meu Eclipse. Porque eu só a abro no caso particular quando eu preciso mudar aquela regra ou quando eu encontrei um problema naquela regra. Mas eu não fico mexendo nela o tempo inteiro.

Essa é a vantagem de uma classe coesa, ela é pequenininha, bem focada, eu sei quando eu tenho que mexer nela, e eu não tenho que mexer nela o tempo inteiro.

No meu slide, eu coloquei a sigla SRP. No começo do curso, eu falei que esse era um curso de Orientação a objetos avançada e SOLID. O que é SOLID? SOLID é o acrônimo, é o conjunto de 5 boas práticas em relação a Orientação a objetos, cada letra fala de uma prática em particular.

O S** nos remete ao **SRP, o Single Responsibility Principle ? em português, Princípio da Responsabilidade Única. A tradução disso, de maneira simples, é coesão. Tenha classes coesas. E aqui, eu discuti com vocês uma maneira de eu conseguir isso, e mais, eu discuti uma maneira de observar que as classes não são coesas.

Comece a prestar atenção nisso no seu dia a dia, quando você está escrevendo uma classe que não para de crescer nunca, esse é um indício de que ela não é coesa. Quando você tem uma classe com 40, 50, 60 métodos, pare e pense “Será que a minha classe, ela tem que ter mesmo 60 comportamentos diferentes? Será que eu não consigo separar isso em classes menores, mais coesas? Então é isso: nesta aula, a lição é coesão. Obrigado.

## Acoplamento e a estabilidade

Bem-vindo à aula de acoplamento do curso de Orientação a objetos avançada do Alura. Nesta aula, eu vou discutir um pouquinho sobre acoplamento.

Acoplamento é um termo muito comum entre os desenvolvedores, em especial entre aqueles que programam usando linguagens OO. Até porque tem aquela grande frase, a máxima da Orientação a objetos, que é “Tenha classes que são muito coesas e pouco acopladas.”

Neste capítulo, em particular, a parte de coesão vou dar uma deixada de lado, e vou discutir um pouquinho mais sobre acoplamento. Mas vamos lá. Dá uma olhada nesse código. Eu tenho um GeradorDeNotaFiscal e se você olhar o método gera, que é o principal método dessa classe, o que ele faz? Ele pega uma fatura, descobre o valor mensal da fatura, gera uma nota fiscal, certo, faz uma conta lá qualquer com o valor da fatura, isso não vem ao caso. Em seguida, ele manda um e-mail, olha lá email.enviaEmail, e depois ela persiste no dao, dao.persiste. E aí retorna a nota fiscal.

Tanto o enviador de e-mail, quanto o dao, eu estou recebendo ali no construtor da classe GeradorDeNotaFiscal. Excelente. Qual que é o problema desse código? Qual que é o problema do ponto de vista de acoplamento desse código?

Pensa o seguinte: hoje, esse código aqui em particular, ele manda e-mail e ele salva no banco de dados usando um dao. Imagina que amanhã ele também vai ter que mandar pro SAP, ele vai ter que mandar um SMS, ele vai ter que disparar um outro sistema da empresa etc.

Essa classe GeradorDeNotaFiscal, ela vai crescer, ela vai passar a depender de muitas outras classes.

A gente acaba sempre aprendendo que acoplamento é uma coisa muita ruim, “nunca acople o seu sistema”, “faça suas classes não serem acopladas”, mas a pergunta é: por quê? Qual que é o real problema do acoplamento? Por que ele é tão ruim assim? Dê uma olhada no diagrama abaixo:

![](https://s3.amazonaws.com/caelum-online-public/solid+com+java/DiagramaSolidJava.jpg)

Hoje, eu tenho um GeradorDeNotaFiscal que depende do EnviadorDeEmail, que depende de um NFDAO, e que depende de um SAP.

O grande problema do acoplamento é que uma mudança em qualquer uma das classes de que eu dependo pode impactar na minha classe principal. Ou seja, se o EnviadorDeEmail parar de funcionar, esse problema pode ser propagado pro GeradorDeNotaFiscal. Se o NFDAO parar de funcionar, o problema vai ser propagado pro gerador. E assim por diante.

Posso até pensar em exemplos de código. Se a interface da classe SAP mudar, essa mudança vai ser propagada para o GeradorDeNotaFiscal. Então, o problema é: a partir do momento em que eu tenho muitas dependências, a minha classe depende de várias.

![](https://s3.amazonaws.com/caelum-online-public/solid+com+java/DiagramaGeradorDeNotaFiscalcomVariasDependendias.jpg)
Quer dizer que eu tenho muitas outras classes que podem propagar problemas pra minha classe principal. E é exatamente por isso que o acoplamento é ruim. Minha classe geradora, ela fica muito dependente, muito frágil, muito fácil de ela parar de funcionar.

Esse é o problema do acoplamento. E ele é bem fácil de ser enxergado, certo? E é por isso que a gente tem que tentar diminuí-lo.

Mas agora a grande pergunta é: será que eu consigo zerar o acoplamento? Ou seja, resolver o problema do acoplamento, nenhuma classe vai se acoplar com ninguém. É impossível. Nós sabemos que, na prática, quando estamos fazendo sistemas de médio, grande porte, dependências existirão. O acoplamento vai existir. Eu vou depender de uma classe, minha classe vai depender de outra, e assim por diante.

O grande ponto aqui é começar a diferenciar os tipos de acoplamento. Quando que o acoplamento é realmente problemático, e quando que ele é problemático, mas não tanto assim? Porque se eu conseguir catalogar, eu vou começar a evitar acoplamentos que são realmente perigosos e me acoplar com coisas que são menos perigosas. Essa é a charada, é aonde eu quero chegar nesta aula.

O ponto é: não é sempre que eu fico incomodado com acoplamento. Alguns acoplamentos eu nem lembro que eu estou fazendo. Por exemplo, em Java, quando eu quero lidar com um monte de elementos, eu normalmente uso uma lista, certo? Quando eu estou escrevendo uma string, eu uso a classe String. String e List são classes do mesmo jeito que qualquer outra classe sua. E quando eu faço uso delas, eu estou me acoplando com elas.

Mas aí que está. Quando eu me acoplo com List, eu não sinto tanta dor no coração quanto eu sinto quando eu me acoplo com um DAO por exemplo. Ou com um EnviadorDeEmail, ou com qualquer outra classe que tenha uma regra de negócio associada. A mesma coisa com String. Eu me acoplo a ela, mas me incomoda menos. É um acoplamento que não me dói.

O ponto é: por quê? Qual é a característica de List e qual é a característica de String que faz com que eu tenha menos medo de me acoplar do que com as outras classes? E veja só que essa é a questão chave, porque se eu descobrir o segredo da interface List, eu vou conseguir replicar esse segredo pras minhas classes. E aí, do mesmo jeito que eu não me importo em me acoplar com List, eu não vou me importar em me acoplar com alguma outra coisa do meu sistema.

Quando que é bom, e quando que é ruim? As pessoas geralmente falam assim: “Puxa, acoplar com List não é problema porque List é uma interface que o Java fez. Vem na linguagem Java”. A mesma coisa com a classe String, “String vem com o Java”. Mas não é bem essa resposta que eu procuro.

A resposta, na verdade, é uma característica que List tem, que String tem, que eu preciso replicar nas minhas classes. Dê uma olhada:

![](https://s3.amazonaws.com/caelum-online-public/solid+com+java/InterfaceList.jpg)

A interface List, quantas implementações ela tem embaixo dela? ArrayList, LinkedList, qualquer outra coisa List. Aqui no desenho eu coloquei GoogleList - o Google tem um monte de bibliotecas que fazem uso da interface List - etc. e tal.

Eu tenho um monte de implementações de List. Além disso, eu tenho muitas classes que usam List, que dependem de List. O seu código, por exemplo, o meu GeradorDeNotaFiscal, suponha que ele depende de List. É um acoplamento também. Agora, imagina que você está nesse cenário. Você programa lá o Java, você está criando a linguagem Java, você tem acesso ao código-fonte de List, ArrayList, LinkedList etc. e eu peço pra você uma mudança na interface List. Você vai fazer essa mudança?

É claro que não! Porque você sabe que essa mudança é difícil. Mudar a interface List implica em mudar a classe ArrayList, a classe LinkedList e assim por diante. List é uma interface muito importante do meu sistema. Eu não posso mexer nela porque eu sei que essa mudança vai quebrar muitas outras classes. Isso faz com que a interface List seja o que chamamos de estável. Ou seja, ela tende a mudar muito pouco. E se ela tende a mudar muito pouco, quer dizer que a chance de ela propagar um erro, uma mudança, pra classe que a está usando é menor. Consegue ver isso?

Ou seja, se a minha classe depende de List, isso não é um problema porque List não muda. Se ela não muda, eu não vou sofrer impacto com a mudança dela. Esse é o ponto. Eu quero me acoplar com classes, interfaces, módulos, que sejam estáveis. Que tendem a mudar muito pouco.

Essa é a diferença de List pro resto. List muda muito pouco. O nome disso em particular – a gente está sempre acostumado a ver o acoplamento daquele ponto de vista onde eu tenho uma classe e eu dependo de várias outras. Classe GeradorDeNotaFiscal depende de NFDAO, de SAP, de EnviadorDeEmail etc. Isso é o que nós chamamos de acoplamento eferente. Eu, classe, dependo de outras. Mas o outro lado do acoplamento, que é o que eu estou mostrando pra vocês na interface List é também importante, e nós chamamos isso de acoplamento aferente. Eu sou uma classe, e o acoplamento aferente mostra quem depende de mim. Olha só a diferença.

E o que isso mostra pra mim? Quando eu tenho muitas outras classes que dependem de uma classe em específico, isso faz com que essa classe seja estável, com que esse módulo seja estável. Então, o acoplamento do outro lado é importante pra dar essa visão pra gente, de coisas que são estáveis. E por que eu quero isso? Porque eu quero me acoplar com coisas estáveis. Dê uma olhada:

![](https://s3.amazonaws.com/caelum-online-public/solid+com+java/DiagramaGeradorDeNotaFiscal2.jpg)

Isso é o que nós temos hoje. GeradorDeNotaFiscal depende de String, depende de List, esse acoplamento me incomoda menos, e aí eu tenho EnviadorDeEmail, NFDAO, SAP e assim por diante. Esses acoplamentos são mais perigosos, pois podem mudar.

O ponto é: como que eu consigo redesenhar isso de maneira a fazer com que o GeradorDeNotaFiscal dependa agora de coisas que são estáveis? Como que eu crio alguma coisa no meu sistema que é estável? Do mesmo jeito que o pessoal lá da Sun (ou da Oracle hoje), fez com List. Eu tenho uma interface List e eu tenho várias implementações embaixo.

![](https://s3.amazonaws.com/caelum-online-public/solid+com+java/GeradorDeNotaFiscal+com+Interface.jpg)

Eu tenho agora o meu GeradorDeNotaFiscal, eu tenho uma interface AcaoAposGerarNota, e essa interface é implementada por SAP, por EnviadorDeEmail e por NFDAO. Imagina só que eu tivesse mais 10 outras implementações embaixo, que são ações que eu executo depois de gerar a nota.

Essa interface que eu acabei de criar, ela acabou de virar estável. A chance de ela mudar vai ser menor. Porque você, programador, vai ter medo de mexer nela. Mexeu nela, criou um método a mais, mudou uma assinatura de algum método, você vai ter que mudar em todas as implementações abaixo. Isso vai fazer com que ela seja estável, naturalmente.

E se eu fizer o meu GeradorDeNotaFiscal parar de depender do SAP, do EnviadorDeEmail, e do NFDAO, e passar a depender agora de um monte de AcaoAposGerarNota, eu resolvi o problema do acoplamento. Porque agora eu dependo de algo que é bastante estável. É por isso, pessoal, que interfaces têm um papel muito importante em sistemas orientados a objetos. É sempre legal aquela ideia de “Programe voltado pra interface”.

Por quê? Porque além de eu ganhar flexibilidade, certo - porque eu posso ter várias implementações embaixo daquela interface -, aquela interface, ela tende a ser estável. E se ela é estável, me acoplar com ela é um problema menor. Tá certo?

Essa é a grande ideia pra reduzir o problema do acoplamento. Não é deixar de acoplar. É começar a acoplar com coisas estáveis, coisas que tendem a mudar menos. Interface é um bom exemplo disso. Interfaces tendem a mudar menos, porque têm um monte de implementação embaixo, porque interface geralmente só tem um contrato, não tem um código ali dentro, isso faz com que ela seja estável.

Vamos ver isso no código. Eu tenho aqui o GeradorDeNotaFiscal, e ela depende do EnviadorDeEmail e do NotaFiscalDao. E eu sei agora que, pra resolver o problema do acoplamento, eu preciso criar uma interface, essa que, mais pra frente, vai ser estável.

```java
public class GeradorDeNotaFiscal {

    private final EnviadorDeEmail email;
    private final NotaFiscalDao dao;

    public GeradorDeNotaFiscal(EnviadorDeEmail email, NotaFiscalDao dao) {
        this.email = email;
        this.dao = dao;
    }

    public NotaFiscal gera(Fatura fatura) {

        double valor = fatura.getValorMensal();

        NotaFiscal nf = new NotaFiscal(valor, impostoSimplesSobreO(valor));

        email.enviaEmail(nf);
        dao.persiste(nf);

        return nf;
    }

    private double impostoSimplesSobreO(double valor) {
        return valor * 0.06;
    }
}
```

Vamos lá. O que eu vou fazer aqui é criar uma interface, Ctrl + N, Interface; vou chamar de AcaoAposGerarNota. Essa interface vai ter um método que eu vou chamar de executa(), e ela vai receber – veja só, todos eles recebem uma nota fiscal, certo, todas as dependências recebem uma nota fiscal – então, vou receber também uma nota fiscal aqui.

`void executa(NotaFiscal nf);`

Aqui no meu gerador, eu vou parar de receber cada um deles em particular (EnviadorDeEmail email, NotaFiscalDao dao) e vou começar a receber uma lista de AcaoAposGerarNota e vou chamar de acoes.

```java
public GeradorDeNotaFiscal(List<AcaoAposGerarNota> acoes)  {

}
```
List, ele vai importar pra mim, é uma lista convencional do java.util. Vou tirar esses caras fora (private final EnviadorDeEmail email; private final NotaFiscalDao dao;), certo, agora eu não preciso mais, porque agora eu tenho uma lista de ações. E aqui, em vez de fazer email.enviaEmail(nf); dao.persiste(nf);, eu vou fazer um loop. Para cada AcaoAposGerarNota acao na lista de acoes, faco acao.executa(nf);.

```java
public class GeradorDeNotaFiscal  {
    private List<AcaoAposGerarNota>  acoes;
    public GeradorDeNotaFiscal(List<AcaoAposGerarNota>  acoes)  {
        this.acoes = acoes;
    }
    public NotaFiscal gera(Fatura fatura)  { 
        double valor = fatura.getValorMensal();
        NotaFiscal nf = new NotaFiscal(valor , impostoSimplesSobre0(valor));
        for(AcaoAposGerarNota acao  :  acoes)  {`
            acao.executa(nf);
        }
        return nnf;
}
```
Pra quem já conhece padrão de projeto, e fez até o nosso curso aqui online sobre o assunto, percebeu que o que eu fiz aqui foi usar o observer. Esse padrão aqui chama observer. E veja só como ele resolve bem o problema do acoplamento. Eu passo a depender de uma lista de ações, AcoesAposGerarNota, e assim que a ação acontece, eu notifico todas as ações pra que cada uma delas faça seu trabalho.

O que eu preciso agora é implementar essas ações. No dao, tenho que implementar AcaoAposGerarNota.

```java
public class NotaFiscalDao implements AcaoAposGerarNota  {

}
```
Tá legal? Erro de compilação, claro, porque o método executa não está escrito, eu vou substituir o nome:

```java
public class NotaFiscalDao implements AcaoAposGerarNota  {
    public void executa(NotaFiscal nf)  {
        System.out.println("salva nf no banco");
    }
}
```

A implementação aqui de exemplo é o sysout, mas na prática, é o código que acessa o banco de dados etc.

A mesma coisa no EnviadorDeEmail. Eu vou implementar AcaoAposGerarNota. Vou mudar o método aqui de enviaEmail para executa, que é o método da interface, e tudo funcionando.

```java
public class EnviadorDeEmail implements AcaoAposGerarNota  {

    public void executa(NotaFiscal nf)  {
```

Agora, basta eu, na hora de instanciar o GeradorDeNotaFiscal, passar todas as ações que eu quero, certo? new NotaFiscalDao, new EnviadorDeEmail, e assim por diante.

Eu resolvi o problema de acoplamento agora do GeradorDeNotaFiscal usando interfaces e, mais do que isso, dependendo de um módulo estável que eu criei, que é o AcaoAposGerarNota. Olha só que simples.

Bem, então o que nós vimos neste capítulo? Nós discutimos um pouquinho do que é acoplamento, e por que ele é ruim. O acoplamento é ruim porque quando eu tenho uma classe que depende de outra classe, mudanças nas classes de que eu dependo podem afetar a classe principal. Isso é problemático. Eu preciso diminuir isso.

Como eu faço isso? Eu tento me acoplar com classes, interfaces, módulos, que sejam estáveis. Um módulo estável é aquele que tenta mudar muito pouco. Ele tem alguma coisa ao redor dele que faz ele mudar muito pouco. E eu mostrei que, no caso da interface, o número de implementações embaixo, o número de pessoas usando aquela interface, são uma força contra mudança nela. Então, acople-se com coisas que são estáveis. E evite ao máximo acoplamento com coisas instáveis no seu sistema. E por hoje é isso, obrigado!

## Classes abertas, Open Closed e Dependency Inversion Principles

Olá pessoal! Nas aulas passadas, discutimos sobre acoplamento e sobre coesão. Sobre acoplamento, em particular, eu falei bastante pra vocês sobre classes estáveis. Você lembra disso? A classe estável é aquela que tende a mudar muito pouco. Qual que é a vantagem disso? A vantagem é que se ela muda muito pouco é melhor que eu me acople a ela, afinal, ela não vai propagar a mudança pra mim.

Sempre que eu quiser pensar em acoplamento, ou precisar me acoplar com alguma outra classe ou módulo, a ideia é que eu me acople com módulos que são estáveis. Ótimo.

Com essa ideia na cabeça, dá uma olhada nesse código aí que eu vou dar pra vocês:

```java
public class CalculadoraDePrecos {

    public double calcula(Compra produto) {
        TabelaDePrecoPadrao tabela = new TabelaDePrecoPadrao();
        Frete correios = new Frete();

        double desconto = tabela.descontoPara(produto.getValor());
        double frete = correios.para(produto.getCidade());

        return produto.getValor() * (1-desconto) + frete;
    }
}

public class TabelaDePrecoPadrao {
    public double descontoPara(double valor) {
        if(valor>5000) return 0.03;
        if(valor>1000) return 0.05;
        return 0;
    }
}

public class Frete {
    public double para(String cidade) {
        if("SAO PAULO".equals(cidade.toUpperCase())) {
            return 15;
        }
        return 30;
    }
}
```
Eu tenho uma calculadora de preço. A ideia dela é basicamente pegar um produto da minha loja e tentar descobrir o preço desse produto. Ele vai primeiro pegar o preço do produto bruto, aí vai usar essa tabela de preços padrão (TabelaDePrecoPadrao) pra calcular o preço, porque pode ter um eventual desconto, em seguida, ele vai descobrir também o valor do frete, porque eu tenho que mandar esse produto pelos correios, e no final, ele faz a conta ali. Ele pega o produto, multiplica pelo valor do desconto, mais o frete, uma regra convencional.

Percebe também que aqui eu tenho várias classes, porque eu estou pensando bastante em coesão, então, idealmente, eu tenho classes pequenas, bastante coesas, com pouca responsabilidade.

Eu tenho essa TabelaDePrecoPadrao, que tem uma regra de negócio, está numa classe. Eu tenho a classe Frete que toma conta ali de calcular o frete também, numa outra classe. E, na calculadora, essa classe depende das outras duas.

Tá legal, está tudo funcionando, e está perfeito. Só que agora pensa no seguinte: imagina que o meu software vá crescer. Então, eu não tenho só a tabela de preços padrão. Eu tenho a tabela de preços padrão e a tabela de preços diferenciados. Pra entrega, eu não uso só os correios. Eu uso os correios, ou estou usando uma outra empresa particular também de entrega de produtos. Imagina que a regra cresceu. Está certo? Eu tenho, de acordo com meu produto, de acordo com o cliente eu calculo o preço de maneira diferente. Como que eu vou implementar isso?

Eu tenho duas maneiras. Vamos lá. A primeira delas seria colocar um if na CalculadoraDePrecos. Dá uma olhada: if(REGRA 1), uma regra qualquer, eu não especifiquei uma regra, mas imagina que eu tenha uma condição qualquer. Se a regra não acontecer, eu vou fazer TabelaDePrecoPadrao e vou usá-la. Caso contrário, se for a REGRA 2, ele vai usar a TabelaDePrecoDiferenciada.

```java

public class CalculadoraDePrecos {

    public double calcula(Compra produto) {

        Frete correios = new Frete();

        double desconto;
        if (REGRA 1){
            TabelaDePrecoPadrao tabela = new TabelaDePrecoPadrao();
            desconto = tabela.descontoPara(produto.getValor());

        }
        if (REGRA 2){
            TabelaDePrecoDiferenciada tabela = new TabelaDePrecoDiferenciada();
            desconto = tabela.descontoPara(produto.getValor());
        }
        double frete = correios.para(produto.getCidade());
        return produto.getValor() * (1 - desconto) + frete;
    }
}
```
A mesma coisa lá pro frete. Aqui eu não coloquei nesse código, mas imagina a mesma coisa. Se cair na regra 1, usa os correios, se cair na regra 2, usa empresa XPTO também de entrega.

Não parece uma boa ideia, certo, porque eu vou começar a encher esse código de if, esse código vai ficar complicado; essa classe começa a perder coesão porque ela começa a saber de muita coisa; o acoplamento vai crescer, porque ela vai depender da TabelaDePrecoPadrao, da diferenciada, dos correios, da empresa XPTO e assim por diante. Está complicado.

Segunda alternativa: seria fazer separado. Eu pego a minha classe Frete e eu coloco esse if na classe de frete. Se eu estou na REGRA 1, faz desse jeito, se eu estou na REGRA 2, faz daquele jeito.
```java
public class Frete {

    public double para(String cidade) {
         if(REGRA 1)  {
              if("SAO PAULO".equals(cidade.toUpperCase())) {
                            return 15;
              }
            return 30;
            }

        if(REGRA 2) { ... }
        if(REGRA 3) { ...}
        if(REGRA 4) { ...}
    }
}
```
A mesma coisa para a TabelaDePrecoPadrao. Se eu estou na REGRA 1, dá esse desconto, se eu estou na REGRA 2, dá aquele outro desconto, e assim por diante.


```java
public class TabelaDePrecoPadrao {

    public double descontoPara(double valor) {
            if(REGRA 1) {
                if(valor>5000) return 0.03;
                if(valor>1000) return 0.05;
                return 0;
        }

        if(REGRA 2) { ... }
        if(REGRA 3) { ...}
        if(REGRA 4) { ...}
    }
}
```
O problema é que também a complexidade vai crescer, certo? Imagina que eu tenho 10 regras, eu vou ter 10 ifs aí, o código vai ficar difícil de manter. Veja só, nos dois códigos que eu dei pra vocês, no primeiro deles o acoplamento ia crescer, porque a classe CalculadoraDePrecos ia começar a depender de muitas outras classes. No meu segundo caso, aqui, a coesão dessas classes Frete e TabelaDePrecoPadrao também ia complicar.

Então, acoplamento, coesão... Eu estou falando pra vocês o tempo inteiro que a grande graça de programar orientado a objetos é balancear entre essas duas coisas. Eu nunca vou conseguir ter máxima coesão e zero acoplamento. A ideia é encontrar esse equilíbrio. Tá legal? Vamos lá.

O primeiro conceito que eu quero passar pra vocês é a ideia de que as classes têm que ser abertas. Mas como assim “aberta”, o que que é uma classe aberta? Eu coloquei aí até a sigla OCP (Open Closed Principle), que é o princípio que fala disso. Mas que raio que é esse negócio de princípio do aberto e fechado, o que são classes abertas?

A ideia é que as suas classes sejam abertas para extensão. Ou seja, eu tenho que conseguir estendê-la, ou seja, mudar o comportamento dela, de maneira fácil. Mas ela tem que estar fechada para alteração. Ou seja, eu não tenho que ficar o tempo inteiro indo nela pra mexer um if a mais, para fazer uma modificação ou coisa do tipo. Então, de novo, fechada para modificação, ou seja, eu não quero ter que o tempo inteiro entrar nela e sair escrevendo código, mas ela tem que estar aberta para extensão, ou seja, eu tenho que conseguir mudar a execução dela ao longo do tempo.

Meio maluco isso, né? Como que eu faço isso? Eu vou mostrar pra vocês em código, e aí vai ficar muito mais claro. Vamos lá. Esse é o código que eu tenho agora. E eu sei que eu quero evitar qualquer tipo de if, sei lá, if(regra1) calcula desse jeito, caso contrário, e assim por diante. Preciso evitar esse if tanto aqui quanto dentro das implementações, de tabela de preço, de frete etc. e tal.

Afinal, está tudo bonitinho, como eu mostrei pra vocês. Esse código é simples, super coeso, esse código aqui do Frete também é simples, super coeso, a CalculadoraDePrecos também.

Mas a gente precisa mudar o comportamento, e é isso que vai acontecer no mundo real. Então, a primeira coisa que eu vou fazer é pensar numa abstração. Já que eu tenho diferentes tabelas de preço, eu preciso pensar numa abstração comum entre todas elas. E, por enquanto, vai ser o próprio método que eu tenho aqui, esse double descontoPara(double valor).

A primeira coisa que eu vou fazer é criar uma interface que vai representar essa abstração pra mim. Eu vou chamar de TabelaDePreco. O único método vai ser double descontoPara.
```java

public interface TabelaDePreco  {
    double descontoPara(double valor);
}
```
Essa classe aqui, TabelaDePrecoPadrao implementa a interface TabelaDePreco:
`public class TabelaDePrecoPadrao implements TabelaDePreco  {`

Vou fazer a mesma coisa pro Frete. Vou chamar aqui (a nova interface) de ServicoDeEntrega. O método que ele vai ter lá é esse double para que recebe uma cidade:

```java
public interface ServicoDeEntrega  {
    double para(String cidade);
}
```
E aqui o Frete vai implementar ServicoDeEntrega:
```java
public class Frete implements ServicoDeEntrega  {
```
Ótimo, está tudo perfeito. E eu sei que essas interfaces, elas tenderão a ser estáveis. Agora, o que eu vou fazer é o seguinte:
```java
TabelaDePrecoPadrao tabela = new TabelaDePrecoPadrao();
Frete correios = new Frete();
```
Esse new é o que me incomoda aqui. Os dois. O ponto é: eu preciso fazer com que seja possível eu trocar a implementação da TabelaDePreco. Como que eu vou fazer isso? Eu vou receber pelo construtor. Então, CalculadoraDePrecos vai ser uma TabelaDePreco no construtor, a interface vou chamar de tabela, e vai receber um ServicoDeEntrega, vou chamar aqui de entrega.
```java
public class CalculadoraDePrecos  {

    public CalculadoraDePrecos(TabelaDePreco tabela, ServicoDeEntrega entrega) {
    }
```
Vou guardar esses dois parâmetros que eu recebi no construtor como atributos da classe, afinal eu vou precisar usar nesses métodos aqui. Vou jogar os dois news fora, e aqui não é mais correios, eu mudei o nome para entrega:
`double frete = entrega.para(produto.getCidade());`

Dá uma olhada nesse código, como está muito melhor. Eu vou até escrever aqui um método de teste para você entender. Eu vou escrever uma classe que se chama Teste, que vai ter uma main qualquer, e aqui vou fazer o seguinte:
```java
public class Teste  {
    public static void main(String[] args)  {
        new CalculadoraDePrecos(tabela, entrega)
    }
}
```
Olha só, dei new nessa classe. Eu preciso passar pra ela uma tabela e uma entrega. Vou criar duas variáveis locais aqui pra ficar mais claro ainda. E agora, qual tabela de preço que eu passo? A que eu quiser! Então, por exemplo, TabelaDePrecoPadrao. Qual serviço de entrega eu passo? new Frete():

```java
public class Teste  {
    public static void main(String[] args)  {
        TabelaDePreco tabela = new TabelaDePrecoPadrao();
        ServicoDeEntrega entrega = new Frete();
```
E olha só, aqui eu tenho a minha calculadora que funciona de um jeito, com a TabelaDePrecoPadrao e com o Frete.
` CalculadoraDePrecos calculadora = new CalculadoraDePrecos(tabela, entrega);`

Quando eu tiver uma outra implementação, então muda para TabelaDePrecoDiferenciada, dá uma olhada. Esse código, vamos fazê-lo compilar rapidinho.

Vou criar a classe, já implementando a interface, certo, o Eclipse é inteligente. Mas veja só! A minha CalculadoraDePrecos continua funcionando! Só que o comportamento dela vai ser diferente, porque, quando ela for usar a TabelaDePreco, ele vai usar qual tabela de preço? A diferenciada.

Veja só que eu consegui mudar o comportamento da CalculadoraDePrecos sem mexer no código dela. Simplesmente porque eu mudei a ferramenta de trabalho, a dependência que ela recebe. Isso que é uma classe aberta para extensão. Eu consigo mudar como que ela vai funcionar, passando, por exemplo, uma dependência pelo construtor.

Por que que isso deu certo? Porque eu pensei bem esse meu código! Eu criei uma abstração TabelaDePreco. É uma interface. Se é uma interface, logo, eu vou ter n implementações. E qualquer implementação vai entrar nessa porta da TabelaDePreco, o polimorfismo vai fazer a mágica pra mim.

Veja só como está muito melhor. Essa classe evolui agora facilmente. Eu consigo mudar a TabelaDePreco, consigo mudar ServicoDeEntrega, e esse código CalculadoraDePrecos está fechado. Porque eu não vou precisar mexer nele. Então, está aberto para extensão, mas está fechado para modificação. Isso é o tal do OCP. Olha só que código bacana, né?!

Legal! Viu o que a gente fez? Eu criei uma abstração, que eu chamei de TabelaDePreco, outra de ServicoDeEntrega, fiz a minha classe CalculadoraDePrecos depender dessas interfaces, e veja bem, essas interfaces são estáveis, elas tendem a mudar muito pouco. Está tudo beleza com o acoplamento, e veja que agora minha classe é aberta! Porque eu consigo mudar o comportamento dela. Então, dependendo da TabelaDePreco que eu passar, a minha calculadora vai funcionar de uma maneira diferente. Dependendo da empresa de frete que eu passar, a minha calculadora vai funcionar também de uma maneira diferente.

Ela está aberta para extensão. E veja só como eu estendi: mudando a implementação que eu passo pras dependências no construtor. E ela está fechada para modificação. Eu não preciso ir nela para mudar o comportamento da TabelaDePreco, eu não preciso ir nela para mudar o comportamento do Frete. Se aparecer um frete novo, eu crio uma nova classe, e a classe CalculadoraDePrecos vai continuar funcionando pra isso.

Isso é o tal do OCP, o Princípio do Aberto e Fechado. E veja só como eu usei, como eu lidei com ele, como que eu joguei com esse problema aí do acoplamento/ coesão. Criei uma interface, que é estável, recebi pelo construtor, e isso fez agora com que eu possa mudar o comportamento da minha classe principal simplesmente mudando a implementação que eu estou passando pra essa classe CalculadoraDePrecos.

Isso é programar orientado a objetos. É pensar em abstração. Quando eu tenho uma boa abstração, eu consigo simplesmente evoluir o meu sistema criando novas implementações das abstrações em que eu já pensei antes. Agora meu sistema está lindo e maravilhoso. Ele evolui facilmente, basta eu criar novas implementações, as classes são todas coesas, são simples, são fáceis de serem testadas de maneira automatizada.

Mas e esse tal do DIP, o Princípio da Inversão de Dependências? Isso você já sabe o que é, eu só não tinha dado o nome. Sabe essa ideia maluca de você sempre depender de classes que são estáveis? Dá pra generalizar esse conceito.

A ideia é: sempre que você for depender, depende de alguém mais estável. Então, A* depende de *B, a ideia é que B* seja mais estável que *A. Mas B* depende de *C. Então, a ideia e que C* seja mais estável que *B. A ideia é que você sempre passe a depender de modos mais estáveis que você.

E mais do que isso, esse princípio vai mais longe. Ele fala o seguinte: “Olha, se você está numa classe, tenta depender de abstração. Você não pode depender de implementação. Dependa sempre de abstrações.”

Se você está numa abstração, a ideia é de que a abstração não conheça a implementação. Consegue ver o caminho? Dependa sempre de abstração, porque abstração é estável. Nunca dependa de implementação.

E a abstração, por sua vez, ela só pode conhecer outras abstrações. A ideia é que ela não conheça detalhes de implementação. Isso é o que nós chamamos de Dependency Inversion Principle, o Princípio de Inversão de Dependência. Não confunda isso com “injeção” de dependência. Injeção de dependência é aquela ideia de você ter os parâmetros no construtor, e alguém magicamente injetar essas dependências pra você. O nome é parecido. Aqui é o princípio da inversão de dependência. A ideia é que você está invertendo a maneira de você depender das coisas. Passa a depender agora de abstrações.

Isso é OCP e isso é DIP. Eu deixei pra falar dele só agora, porque agora você entende bem o que é coesão, entende bem o que é acoplamento, e entende estabilidade. Agora você tem ferramenta suficiente pra jogar e entender essa ideia aí do OCP.

Sempre que eu programo, eu penso muito em abstração. O tempo inteiro. Porque a abstração vai me dar um monte de vantagem. Ela vai deixar que minha classe seja aberta o tempo inteiro, então eu posso mudar, criar uma nova implementação, e minha classe que depende da abstração vai funcionar com ela. A abstração é estável, então ela não vai propagar mudança problemática pra classe principal, e assim por diante.

Programar orientado a objetos é pensar em abstração. Quando eu estou dando aula de Orientação a objetos básica, e aí o cara está vendo pela primeira vez todas aquelas ideias malucas de polimorfismo, herança, encapsulamento etc. e tal, uma brincadeira que eu sempre faço com eles é: no meio da aula eu falo “Gato, cachorro e pássaro”. Eu espero que eles me respondam “animal”. Eu viro e falo “ISS, INXPTO e sei-lá-o-que-das-quantas”, outros nomes de imposto, e eu espero que a pessoa me fale “imposto”. Eu faço o tempo inteiro o meu aluno pensar em abstração. Isso é programar orientado a objetos. É pensar primeiro na abstração, e depois, na implementação.

Essa é uma mudança de pensamento com quem programa procedural. Porque no mundo procedural, você está muito preocupado com a implementação. E é natural. No mundo OO, você tem que inverter: a sua preocupação maior tem que ser com a abstração, com o projeto de classes.

Pense no seu projeto. A implementação é importante, o código ali que vai fazer a coisa funcionar, o if, o for, é importante. Mas no sistema OO, pensar no projeto de classes é fundamental. É isso que vai garantir a facilidade de manutenção.

Eu tinha lá a minha CalculadoraDePrecos e agora eu dependo de uma interface Frete, dependo de uma interface TabelaDePrecos. E aí basta eu passar implementações concretas de cada um deles, que a minha CalculadoraDePrecos vai mudar.

Resumindo, falei nesse capítulo pra vocês de classes abertas, e o tal do OCP (o Princípio do aberto e fechado) – a ideia é que suas classes sejam abertas para evolução, mas fechadas pra mudança. E eu falei pra vocês do DIP (Dependency Inversion Principle), cuja ideia é você inverter a dependência, e sempre depender de abstrações. Porque abstrações são legais, são estáveis etc. e tal.

Esse foi o conteúdo dessa aula, e isso mostra pra gente as outras duas letrinhas aí do SOLID, o O** do OCP, e o **D do DIP.

## Entendendo o encapsulamento

Olá! Bem-vindo à aula cujo tema é encapsulamento. Nós já discutimos até agora acoplamento e coesão. Falta o 3o. pilar aí, que é esse tal de encapsulamento, certo? Vamos começar do jeito que estamos começando todos os nossos capítulos até então, que é com código. Dá uma olhada nessa classe:

```java

public class ProcessadorDeBoletos {

    public void processa(List<Boleto> boletos, Fatura fatura) {
        double total = 0;
        for(Boleto boleto : boletos) {
            Pagamento pagamento = new Pagamento(boleto.getValor(),   
        MeioDePagamento.BOLETO);
            fatura.getPagamentos().add(pagamento);

            total += fatura.getValor();
        }

        if(total >= fatura.getValor()) {
            fatura.setPago(true);
        }
    }
}
```

Eu tenho um ProcessadorDeBoletos. O método processa deixa bem claro que, dadas uma lista de boletos e uma fatura, ele passeia por cada boleto, gera um pagamento, associa esse pagamento à fatura. E aí ele tem essa variável de total, onde ele vai somando o valor de todos os boletos. E no fim, ele faz uma verificação: se o valor da fatura for menor do que o total pago, quer dizer que a fatura já foi paga por completo. E aí ele marca a fatura como paga. Tá certo? Um código também do mundo real, certo, isso parece bastante com aqueles Batch Jobs que estamos acostumados a escrever. Aqueles processos que rodam por trás dos panos pra fazer verificação.

Tenho certeza de que todo mundo ali que lida com pagamento e recebe pagamento em boleto, se fez a implementação na unha, ele tem um códigozinho por trás que faz mais ou menos a mesma coisa.

Agora, a pergunta de sempre: qual que é o grande problema desse código?

De volta a ele, vamos lá. Acoplamento. Acoplamento não parece tão ruim, certo? Porque eu estou dependendo de Boleto, de Fatura, não tenho muito como fugir disso, eu estou numa regra de negócio que envolve essas duas entidades. Acoplamento com List já discutimos que não é problemático, certo, List é uma interface muito estável etc. e tal. Então, não é acoplamento.

Coesão. Vamos olhar esse código. Esse código também faz uma única coisa, ele só toma conta da regra de processar um boleto. Ele não está lá tão bonito, eventualmente eu podia tentar extrair algum método privado e tudo mais, mas também não é problema de coesão.

O problema que está acontecendo aqui é um problema de encapsulamento. Veja só. Essa classe se chama ProcessadorDeBoletos. A ideia é ela processar boletos, os n boletos porque o usuário pode pagar com mais de um boleto, né? É por isso que a gente está recebendo uma List. Mas a pergunta é: e amanhã? E se eu fizer o ProcessadorDeCartaoDeCredito?

Repara que, nesse código em particular, eu tenho uma regra de negócio que está no lugar errado. Está vendo este total que pega o valor do boleto, e lá embaixo o if pra ver se o valor é maior ou igual ao da fatura?

```java
        total += boleto.getValor();

    } 
    if(fatura.getValor()<= total)  {
       fatura.setPago(true);
    }
```

Essa é uma regra que não deveria estar no ProcessadorDeBoletos. Essa é uma regra da fatura. Isso devia estar escondido na classe Fatura. Qual que é o problema disso? Qual que é o problema de esta regra estar jogada aí?

O problema é que amanhã, se aparecer o ProcessadorDeCartaoDeCredito, igual eu falei, você vai ter que repetir esse código. Agora imagina que eu tenha 3, 4 processadores diferentes. No momento em que eu tiver que mudar alguma regra dessas - sei lá, mudei a regra de quando eu marco uma fatura como paga - eu vou ter que sair buscando no meu código onde que eu reescrevi essa regra de negócio. Tudo isso por quê? Porque essa regra não está escondida. Veja só, a fatura é que deve ser a responsável por se marcar como paga. Ela que sabe o momento de ela estar paga e o momento de ela não estar paga. Até porque, veja só, ela tem a lista de pagamentos. Essa regra podia estar lá dentro, sem qualquer dor de cabeça do ponto de vista de implementação.

Esse é um problema de encapsulamento. Encapsular é conseguir esconder o comportamento dentro da classe. Quando eu quebro o encapsulamento ou quando eu vazo o encapsulamento, o que acontece é que eu coloco uma regra de negócio fora do lugar em que ela deveria estar. E o problema é esse que vocês estão vendo. Eu tenho que usar do Ctrl + C e Ctrl + V se eu quiser ter essa regra em vários lugares diferentes. Não é tão simples quanto invocar um método.

Vamos lá, próximo código.

```java
NotaFiscal nf = new NotaFiscal();
double valor;
if (nf.getValorSemImposto() > 10000)  {
    valor = 0.06 * nf.getValor();
}
else {
    valor = 0.12 * nf.getValor();
}
```

Dá uma olhada. É um problema parecido: eu tenho uma NotaFiscal, e tenho um if aí “Olha, se o valor da nota sem imposto for maior que 10mil, eu vou calcular o valor da nota de um jeito. Caso contrário, eu vou calcular de outro jeito”.

Veja só, esse é um outro exemplo de código que está mal encapsulado. Tá certo? O cara que tem esse código – imagina que é uma outra classe qualquer, com esse trecho de código – ele sabe demais de como funciona uma nota fiscal. E quem que deve saber como que funciona uma nota fiscal? A própria classe nota fiscal.

Nós até chamamos códigos como esse, que entendem demais de como a outra classe funciona, nós chamamos esse mau cheiro de código de intimidade inapropriada. Por quê? Por que esse código conhece demais a nota fiscal, ela sabe que se o valor for maior que 10mil ela tem que fazer assim, se não, ela tem que fazer assado. Não é legal. Veja só como que eu resolveria isso, nesse outro trecho de código, eu tenho isso aqui:

```java
NotaFiscal nf = new NotaFiscal();
double valor = nf.calculaValorImposto();
```

E aí a regra de calcular o imposto está escondido na NotaFiscal. Muito melhor, muito mais fácil. Em todo lugar que eu precisar saber o valor do imposto, basta invocar esse método. Se um dia eu tiver que mudar a regra de negócio, eu vou mudar num único ponto.

Um princípio de Orientação a objetos, galera, agora que eu já mostrei pra vocês esses dois lados, do código que está íntimo demais, e do código encapsulado, um desses princípios é o que nós chamamos de Tell, Don’t Ask, ou seja, “Diga, não pergunte”. Mas como assim, diga e não pergunte?

Dá uma olhada. No código de cima, a primeira coisa que eu estou fazendo é uma pergunta.

```java
NotaFiscal nf = new NotaFiscal();
double valor;
if (nf.getValorSemImposto() > 10000)  {
    valor = 0.06 * nf.getValor();
}
else {
    valor = 0.12 * nf.getValor();
}
```
E aí, de acordo com a resposta dessa pergunta, eu tomo uma ação: eu calculo o valor de um jeito ou calculo o valor de outro. Isso é perguntar. Está certo? Quando eu tenho um código que pergunta uma coisa pra um objeto, para aí tomar uma decisão, é um código que não está seguindo o Tell, Don’t Ask. Ou seja, eu tenho que dizer pro objeto o que ele tem que fazer. Eu não tenho que perguntar, para depois tomar a decisão.

O segundo código aí já faz isso direito:
```java
NotaFiscal nf = new NotaFiscal();
double valor = nf.calculaValorImposto();
```

Eu estou mandando o objeto calcular o valor do imposto. Lá dentro, óbvio, a implementação vai ser esse if aí, não tem como fugir disso. Mas agora eu estou mandando o objeto fazer alguma coisa. Sempre que você tiver códigos como esse, que perguntam para tomar uma decisão – e isso é bem característica de código procedural, certo, porque quando você está programando lá em C você não tem muito como fugir disso. Isso é programação procedural.

Aqui no mundo OO, eu tenho que mandar nas coisas. Eu mando meu objeto fazer alguma coisa, eu não pergunto. A partir do momento em que eu pergunto para tomar uma decisão, muito provavelmente eu estou furando meu encapsulamento aí. Está legal? Agora vamos lá. Vamos tentar descrever bem o que é um código encapsulado. Quando eu olho para um código, por exemplo este:

```java
NotaFiscal nf = new NotaFiscal();
double valor = nf.calculaValorImposto();
```

Eu quero saber se isso está bem encapsulado. Eu faço pra mim duas perguntas, a primeira é: O que esse método faz? E como que eu sei isso? Eu sei o que o método faz pelo nome dele, calcula o valor do imposto. É um nome bem semântico, deixa claro pra mim que ele está calculando o valor do imposto. Ótimo, consegui responder essa pergunta, então está legal.

A próxima pergunta é: Como que ele faz isso? Ou seja, como que ele calcula o valor do imposto? Se eu olhar só pra esse código, eu não sei responder. Eu não sei dizer qual que é a regra que ele está usando por debaixo dos panos. Eu não sei qual é a implementação do calculaValorImposto(), e isso, na verdade, é uma coisa boa. Eu nunca posso saber como que um método faz alguma coisa. Eu tenho que deixar isso escondido, eu tenho que deixar isso encapsulado nele.

Se o método esconde bem, se ele esconde como ele faz o que ele deixa bem claro pelo nome, se ele esconde esse “como” ele está fazendo, o que eu ganho? Eu ganho que eu posso trocar essa implementação sem nenhum problema. Se eu entrar no código calculaValorImposto() e mudar a regra, as classes clientes não serão afetadas, todas elas continuarão a funcionar com a regra nova.

Lembra do código que eu dei no começo? Onde eu tinha lá aquele if primeiro, para depois tomar a decisão? Se eu mudar a regra de negócio ali, ela não vai se propagar para todo lugar do meu sistema. Está certo? Isso é um código encapsulado. O código encapsulado é o código que esconde como aquele método faz a tarefa dele.

Um exemplo bem comum de código bem encapsulado – isso as pessoas acertam na maioria das vezes nos códigos OO – são os DAOs. O DAO é aquela classe que acessa um banco de dados, acessa uma fonte de dados qualquer. Geralmente, você faz lá new NotaFiscalDao, por exemplo. E aí você faz nf.pegaTodos(). O que esse método faz? Pega todas as notas fiscais. Como ele faz? Não sei! Não sei se vem de um banco de dados, se ele está consumindo um serviço web, se ele está lendo um arquivo texto. Tudo isso está escondido dentro da implementação desse pegaTodos().

Pessoal, uma coisa bastante interessante de você pensar quando você programa OO é você não só pensar na implementação que você está escrevendo naquele momento, mas também pensar nas classes clientes, nas classes que vão usar seu código. Em sistemas OO, é isso que geralmente complica um código. É isso que geralmente faz um sistema difícil de manter. Sistemas difíceis de manter são aqueles que não pensam na propagação de mudança. É um sistema em que você, para fazer uma mudança, tem que mudar em 10 pontos diferentes. Códigos bem encapsulados geralmente resolvem esse tipo de problema, porque você muda num lugar e a mudança se propaga.

Se você pensar no sistema OO como um quebra-cabeça, você tem uma peça e as outras peças se encaixam nesse quebra-cabeça. Se o desenho da sua peça está feio por dentro, não tem problema: você pode apagar o desenho dessa peça e melhorar, deixa-lo mais bonito. O problema são os encaixes dessa peça. Se tem muita peça ao redor usando a sua peça, mudar o desenho é difícil. Certo? Esse é o ponto.

Quando você programar orientado a objetos, não pense só no código que você está escrevendo, pense no código que você está usando. Sempre que eu programo, eu tenho duas classes abertas: a classe que eu estou programando – e aquela é a classe principal, por exemplo, a classe NotaFiscal ? e eu tenho também uma classe que pode ser um método main qualquer, onde eu experimento a minha nota fiscal. Então, eu escrevo a NotaFiscal, e escrevo uma main, e eu uso essa nota fiscal. Pra eu ver se a interface que eu estou programando está bonita. Se está coesa, se está encapsulada, se meu código não está muito acoplado e assim por diante.

Eu, na prática, não uso um método main. Eu escrevo um teste automatizado pra isso. Se você não sabe o que é um teste, faça o nosso curso de Teste de unidade e TDD. Você vai ver que tem muita relação entre o código que é bastante orientado a objetos e um código fácil de ser testado. Então eu aproveito e mato 2 coelhos com uma única cajadada. Porque eu acabo escrevendo um teste; esse teste me ajuda a verificar se meu código está coeso ou se não está coeso; e aí meu sistema acaba saindo testado.

Mas teste também não é o ponto desse meu curso corrente. Dá uma olhada lá na nossa formação de teste, que tem bastante coisa.

Isso é encapsulamento. É quando eu consigo encapsular e esconder como as classes fazem as tarefas delas. Escondendo, isso quer dizer que as classes clientes não conhecem da implementação, e, por consequência, eu posso mudar a implementação à vontade, que os clientes não vão nem perceber que isso aconteceu. Tá certo? Isso é um código encapsulado. Encapsule o tempo inteiro.

Outro exemplo, que é bastante importante. Dá uma olhada nesse código aqui:

```java
public void algumMetodo()  {
    Fatura fatura = pegaFaturaDeAlgumLugar();
    fatura.getCliente().marcaComoInadimplente();
}
```

Eu tenho um método qualquer, eu tenho uma fatura, e eu faço fatura.getCliente().marcaComoInadimplente();. Eu tenho certeza de que você já escreveu um código parecido com esse. A.getB.getC.getD.metodoQualquer();. Você tem aí uma cadeia de invocações. Qual que é o problema disso? O problema disso é que eu também estou furando o encapsulamento nesse caso.

Imagina só que, por algum motivo, a classe cliente muda. Mudou o [TODO 12’25 API] dela, ela não tem mais esse método marcaComoInadimplente(). Onde que meu código vai quebrar? Meu código vai quebrar em todo lugar que usa um cliente e em todo lugar que usa uma fatura, que usa um cliente ao mesmo tempo. Tá certo? Que usa um cliente, como nesse código em particular, de maneira indireta, porque fatura.getCliente().marcaComoInadimplente();

Esse é o problema de invocações em cadeia, A.getB.getC.getD. Se B mudar, ou se C mudar, ou se D mudar, o seu código vai quebrar. A ideia é que, se você precisa marcar o cliente, uma fatura como inadimplente, você faça alguma coisa parecida com isso aqui:

```java
public void algumMetodo() {
    Fatura fatura = pegaFaturaDeAlgumLugar();
    fatura.marcaClienteComoInadimplente();
}
```

Dentro lá da fatura, você vai fazer Cliente.marcaComoInadimplente, certo, vai repassar a invocação, não tem problema.

Mas o ponto é que você encapsulou a maneira com que a fatura faz pra marcar um cliente como inadimplente.

Ah, mas e se meu cliente mudar?
Tudo bem! Se ele mudar, a classe Fatura vai parar de funcionar, mas eu vou mexer num único lugar, que é na classe Fatura. Lembra que a ideia é diminuir pontos de mudança. Eu prefiro ter que mexer no cliente e na fatura, do que ter que mexer no cliente e na fatura, em todo mundo que mexe em cliente, e em todo mundo que mexe em cliente de maneira indireta através da Fatura. Eu diminuo ao máximo pontos de mudança.

No mundo OO, tem até essa Lei de Demeter, que é mais ou menos famosa, e ela diz mais ou menos isso: Olha, evita ao máximo a ideia de você fazer essas invocações em cadeia. A.getB.getC etc., invoca um método qualquer, como é o exemplo desse código. Por quê? O que eu estou ganhando quando eu sigo a Lei de Demeter na maioria dos casos?

Eu estou ganhando encapsulamento. Estou escondendo meu código. E o que eu ganhei com encapsulamento? Diminuo propagação de mudanças. Certo? Legal.

Vamos então agora refatorar aquele código do nosso ProcessadorDeBoletos pro código mais encapsulado, pra você ver como é fácil.

Então vamos lá, vamos corrigir esse código. Sabemos que o problema do encapsulamento está aqui, certo?

```java
   if(total >= fatura.getValor()) {
            fatura.setPago(true);
   }
```

A fatura não pode ser marcada como pago por esse código ProcessadorDeBoletos. Essa regra de negócios tem que estar lá dentro do Fatura. Ou seja, aqui dentro, eu tenho que achar um bom lugar pra colocar isso.

A primeira coisa que eu vou fazer é começar tirando esse setPago:

```java
    public void setPago(boolean pago) {
        this.pago = pago;
    }
```
Getters e setters, pessoal, são uma coisa bastante perigosa no mundo Java, porque, a partir do momento em que você dá um setter pra um atributo da sua classe, você está dando a oportunidade de qualquer cliente mudar aquele valor de qualquer jeito. E nem sempre eu quero isso. Por exemplo, às vezes eu quero, sim, um método [TODO 15’05 setRua()] na minha classe Endereco, porque mudar rua não tem segredo, é basicamente mudar uma informação pela outra.

Agora, se está numa fatura como paga ou não, nesse meu problema em particular, tem uma regra de negócio associada. Então, não posso querer que qualquer um do mundo de fora consiga simplesmente falar: está pago, ou não está pago. Essa regra tem que estar escondida em algum lugar, aqui na Fatura. Getters e setters, pessoal, setters mais em particular, muito cuidado na hora de criá-los. Porque você pode eventualmente estar furando seu encapsulamento.

Sabe aquela coisa que você fez no primeiro dia de Java, que você criou getters e setters pra tudo? Cuidado, não é bem assim. Getters são menos problemáticos, porque você simplesmente está dando uma informação pro cliente, pro usuário de fora, e, a não ser que essa informação seja uma outra classe, que não esteja encapsulada, ele vai conseguir mudar alguma coisa. Mais setters, não, você está dando a chance de ele fazer qualquer coisa na sua classe.

Imagina só se eu desse aqui, por exemplo, um setPagamentos(List<Pagamento> pagamentos). Estou dando a chance pra ele passar uma lista de pagamentos pra mim, jogar a antiga fora, passar uma nova – não sei se essa é a melhor alternativa. Tá certo? Então eu sempre penso bastante antes de sair criando setters. Veja mesmo que essa classe Fatura não tem nenhum agora. O único era aquele setPago que era pra eu motivar a discussão com vocês, mas agora eu joguei fora.

Então aqui dentro eu preciso de um método, e esse método vai tomar uma decisão: se a fatura está paga ou não. Voltando aqui pro meu código, deixa eu jogar isso aqui fora, que eu não preciso mais,

```java
    } 
    if(fatura.getValor()<= total)  {
       fatura.setPago(true);
    }
```

O total += boleto.getValor(); também não vai estar aqui, e essa regra double total = 0 não vai estar aqui.

Veja só, fatura.getPagamentos().add(pagamento);. Discutimos já a Lei de Demeter, certo, isso aqui fura um pouco. Eu estou enfiando um pagamento, que está associado a uma fatura, e a fatura nem percebeu que isso aconteceu. Veja só, não parece uma boa ideia, até porque a regra já diz isso. Adicionou um pagamento, esse pagamento que foi efetuado pode fazer com que a fatura mude de estado. Não é assim que funciona? O cara fez um pagamento. Se o pagamento for maior do que o valor da fatura, ela está paga.

Veja só como eu vou resolver isso. Em vez de fazer fatura.getPagamentos().add(pagamento);, eu vou fazer fatura.adicionaPagamento(pagamento);. E vai passar esse pagamento aqui.

Eu vou criar esse método, e veja só a implementação:

```java
public void adicionaPagamento(Pagamento pagamento)  {
    this.pagamentos.add(pagamento);
```

Ótimo. A fatura está tomando conta da estrutura dela, então a fatura sabe que ela tem uma lista de pagamentos lá dentro, estou adicionando. Legal. E aqui dentro, vou verificar se o valor total dos pagamentos é maior do que o valor da fatura. Se isso for verdade, eu vou setar a fatura como paga:

```java
    if(valorTotalDosPagamentos()>=this.valor)  {
        this.pago = true;
    }
```

O valorTotalDosPagamentos, vou implementar aqui só pra gente ver como fica. Fazer uma implementação simples mesmo:

```java
private double valorTotalDosPagamentos()  {
    double total = 0;    

    for(Pagamento p : pagamentos)  {
    total += p.getValor();
    }
    return total;
}
```

Nem é a melhor implementação do mundo, porque eu tenho um loop aqui, e dentro, um outro loop, então poderia cachear esse valor, e coisa e tal. Mas lembra daquilo que nós discutimos: implementação, código problemático, o algoritmo mais complicado que deveria ser, é um problema fácil de resolver. Depois é só vir aqui e mudar o comportamento, e tudo vai continuar funcionando.

O importante nessa aula aqui é perceber que agora esse código está encapsulado. Veja só, eu crio um pagamento, e eu faço fatura.adicionaPagamento(pagamento);. O que esse método faz? Adiciona um pagamento. Como ele faz, quais são as regras dentro dele? Daqui eu não sei.

```java
public class ProcessadorDeBoletos {

        public void processa(List<Boleto> boletos, Fatura fatura) {

            for(Boleto boleto : boletos) {
                       Pagamento pagamento = new Pagamento(boleto.getValor(),
                        MeioDePagamento.BOLETO);
            fatura.adicionaPagamento(pagamento);
            }
           }
}
```

Aqui dentro do Fatura, veja só, que a gente implementou aquela regra do estar pago, do não estar pago. Está certo?

```java
public void adicionaPagamento(Pagamento pagamento)  {
    this.pagamentos.add(pagamento);
    if(valorTotalDosPagamentos()>this.valor)  {
    }
}
```

Isso é um código encapsulado. Quando eu falei pra vocês de getters e setters, aliás, eu falei pra vocês que setters são perigosos. Getters são menos problemáticos, certo, porque se você devolver esse getCliente aqui:

```java
    public String getCliente() {
        return cliente;
    }
```

No ProcessadorDeBoletos, do lado de fora, se eu fizer, por exemplo:

```java
public class ProcessadorDeBoletos {

    public void processa(List<Boleto> boletos, Fatura fatura) {
    String nomeDoCliente = fatura.getCliente();
```

Ele vai me devolver uma string com o nome do cliente. Mas isso não tem problema, porque se eu mudar o nome do cliente, essa variável aqui, isso não vai afetar minha fatura. Não é o que acontece com a lista. Tanto é que o código antigo era fatura.getPagamentos().add(), certo? Se eu não quero que isso aconteça, eu posso bloquear essa classe Fatura.

Eu vou dar o getPagamentos, mas aqui eu vou usar e abusar da API do Java, vou fazer assim:

```java

public List<Pagamento> getPagamentos()  {
    return Collections.unmodifiableList(pagamentos);
}
```

O que isso vai fazer pra mim? Ele vai me devolver uma lista de pagamentos, então aqui eu posso continuar usando fatura.getPagamentos() sem problema. Ele vai deixar eu ler essa lista, mas se eu tentar escrever na lista, esse código vai me lançar uma exceção.

Então, essa é outra dicazinha que eu dou pra vocês, que eu sempre faço. Se eu quero que a minha classe seja bastante encapsulada, e eu não quero que os clientes saiam pegando as minhas listas e enfiando coisas dentro dela, sem eu saber o que está acontecendo, eu dou o getter pra lista mas não deixo ele modificar. Agora, toda mudança na lista de pagamentos tem que ser feita pela classe Fatura. Eu escondi toda a ideia de você manipular essa lista de pagamentos na classe Fatura.

Está claro agora, está escondido, está encapsulado. Muito melhor. Legal, nosso código está bem mais encapsulado agora. Então, vamos lá. O que é encapsulamento? Encapsulamento é esconder como a classe implementa as suas tarefas. Como que eu sei que as minhas classes e métodos estão encapsulados? Fácil! Basta eu olhar pra ele, ver o nome dele – pega uma classe que o está usando – e tenta responder as duas perguntas: O quê? E como?

O “O quê?” você tem que ser capaz de responder, porque o nome do método tem que te dizer isso. O “Como?” você não tem que conseguir responder. Está certo? Se você conseguiu responder como a classe faz aquela tarefa – “Ah, ela faz, ela acessa o banco de dados, que eu sei que é um banco de dados porque eu estou tendo que passar a connection do banco de dados pra ela”, ou qualquer coisa do tipo, ou “Ah, eu sei que ela marca a fatura como paga, porque o código está aqui, eu estou vendo um if na minha frente, if nota fiscal maior do que tanto, marca como, sei lá, pago, inativo, eu não sei. Eu estou vendo o código ali”. Não está encapsulado. Resolvo o problema de encapsulamento, e isso vai lhe garantir aí depois umas boas horas de sono, porque vai lhe dar menos trabalho para fazer uma mudança do seu usuário final.

Então isso é encapsulamento. Eu espero que essa aula o tenha ajudado a perceber quando seu código não está encapsulado e mostrar como resolver esse problema. Esconda o código, encapsule o código sempre nos lugares certos. Obrigado!

## Herança e o Liskov Substitutive Principle

Olá! Neste capítulo, eu vou falar um pouquinho pra vocês sobre herança. Herança, lá no começo das linguagens orientadas a objeto, era o grande marketing delas, porque “nossa senhora, você vai conseguir reaproveitar código de maneira fácil, basta colocar a palavrinha “extends” aqui, ou dois pontos lá no C#, não sei que das quantas, e seu código vai ser reutilizado sozinho, a classe filho vai ganhar os métodos da classe pai e tudo mais.” E isso foi um grande fator de “venda” da orientação a objetos, mas hoje, a indústria percebeu que não é lá tão fácil assim usar herança.

Eu vou mostrar pra vocês, como sempre, com exemplo. Dá uma olhada aqui nessa classe ContaComum:
```java

public class ContaComum {

    protected double saldo;

    public ContaComum() {
        this.saldo = 0;
    }

    public void deposita(double valor) {
        this.saldo += valor;
    }

    public double getSaldo() {
        return saldo;
    }

    public void rende()      {
        this.saldo*= 1.1;
    }    
}
```

Ela tenta representar, obviamente, de maneira simplificada, uma conta de um banco. Dá uma olhada. Eu tenho ali um método deposita, um método getSaldo e o método rende. Esse método rende, ele faz uma conta qualquer com o saldo. Multiplica por 1.1 e dá o rendimento pra essa conta.

Agora imagina que no meu banco apareceu agora ContaDeEstudante

```java

public class ContaDeEstudante extends ContaComum {

    public void rende()  {
        throw new ContaNaoRendeException();
    }
}
```

A ContaDeEstudante é igualzinha a uma conta comum, com a diferença de que ela não rende. Então, dá uma olhada. Classe ContaDeEstudante que extends de ContaComum, e aí, sobrescrevi o método rende, e lancei uma exceção: throw new ContaQueNaoRendeException. Certo? Normal. Sobrescrevi o método na classe filho, mudei o comportamento pra alguma coisa diferente.

É assim que fazemos, certo? Ótimo. Só que esse código, apesar de pequeno, tem um problemão. Que problema que é esse? Dá uma olhada aqui nesse método main simples da vida:


```java
public class ProcessadorDeInvestimentos {

    public static void main(String[] args) {

        for (ContaComum conta : contasDoBanco()) {
            conta.rende();

            System.out.println("Novo Saldo:");
            System.out.println(conta.getSaldo());
        }
    }
}
```

Eu tenho uma lista de contas do banco, certo, e eu estou tratando-as como uma conta comum, afinal eu posso, vou usar polimorfismo aqui pra tratar todas elas a partir da abstração maior, que é a ContaComum, e estou fazendo um loop. Aí eu fiz conta.rende.

Se eu rodar este programa, eu não sei o que vai acontecer, porque se o banco só tiver contas comum, tudo vai funcionar. Mas se o banco tiver uma conta de estudante, opa! Esse código não vai funcionar, porque o método rende vai lançar uma exceção. Então veja só, imagine que meu sistema já existe, e eu só tenha contas comuns, e eu tenha um monte desses loops espalhados pelo meu sistema. E vários deles invocam esse método rende. Agora imagina que eu criei a classe filho ContaDeEstudante e sobrescrevi um método com esse throw new e passei uma nova exceção.

Puxa, um monte de código que já funcionava vai parar de funcionar. Isso não é uma boa ideia. Veja só, eu criei uma classe filho e essa classe filho quebra o comportamento das outras classes do sistema que usavam antes a abstração ContaComum.

Veja só que na hora de usar herança, isso não é tão fácil assim. Vou dar um outro exemplo pra vocês. Veja só esse exemplo aqui:

![](https://s3.amazonaws.com/caelum-online-public/Solid+com+Java/1_1+mostrando+o+exmplo.png)

Eu tenho a classe Retangulo e aí eu tenho uma classe filho dela, que é a classe Quadrado. Esse é um exemplo bastante comum, muito professor usa esse exemplo na primeira aula de herança, e coisa e tal.

A classe Retangulo é simples, ela tem 2 lados, porque todo retângulo tem 2 lados, e tem lá qualquer método, qualquer comportamento dessa classe retângulo. O que ele faz? Ele quer criar a classe Quadrado. E ele sabe que o quadrado é um tipo especial de retângulo, certo? O quadrado é aquele retângulo cujos dois lados são iguais. Então, ele vai lá e faz classe Quadrado que extends Retangulo. Faz uma classe ser filho da outra.

Isso não é uma boa ideia. O que estou fazendo aqui é exatamente a mesma coisa que eu fiz lá na classe ContaComum com a classe ContaDeEstudante. Tem um princípio da Orientação a objetos, pessoal, cuja sigla é LSP. No SOLID é o L, certo? A sigla LSP significa Liskov Subtitutive Principle, Princípio de Substituição de Liskov.

Qual é a ideia? A pesquisadora, na época – e esse artigo é antigo, é de por volta de 85, 86, 87 – percebeu que, pra você usar herança, você tem que pensar muito bem nas pré-condições da sua classe e nas pós-condições da sua classe. Se você pensar, todo método, quando recebe parâmetros, você tem pré-condições: “Ah, o inteiro que eu estou recebendo no método Saldo, no método X, no método fazUmDepósito, sei lá o quê, esse valor pode ser qualquer coisa que seja positiva, tem que ser maior que 0. No deposita, a mesma coisa. O double que eu recebo tem que ser maior que ). O retorno do método, não sei, getSaldo, é sempre um valor positivo, nunca é menor que 0, e getSaldo nunca retorna uma exception.

Então, todo método tem lá as suas pré-condições e as suas pós-condições. Como que ela vai receber os dados de entrada, quais são as constraints, as restrições dos dados de entrada, e quais são as restrições do dado que ela gera como uma saída.

Esse exemplo aqui do retângulo e do quadrado deixa bem claro isso, que as pré-condições de um e de outro são diferentes. Veja só, no retângulo, os lados não têm pré-condições, eles podem ser quaisquer números, inclusive diferentes. No quadrado, a pré-condição é diferente: os dois lados têm que ser iguais. E ela mostra – o Princípio de Liskov mostra – que, quando você tem uma classe filho, a classe filho nunca pode apertar as pré-condições. Você nunca pode criar uma pré-condição que seja mais restrita do que da classe pai.

A classe filho só pode afrouxar a pré-condição. Pensa no caso onde eu tenho a classe pai, e a classe pai tem um método que pode receber inteiros de 1 a 100. Aí a classe filho muda isso, ela só deixa receber inteiros de 1 a 50. Veja só que 1 a 50 é mais restritivo do que 1 a 100. Eu apertei a pré-condição. Isso pode complicar as classes clientes, porque as classes clientes sabem que a classe pai pode receber de 1 a 100. Então elas vão passar de 1 a 100 sem pensar muito.

Só que, se a classe filho apertou essa restrição, quer dizer que a classe filho vai ter um comportamento que é inesperado.

Com a pós-condição, é a mesma coisa. A classe filho, ela nunca pode afrouxar a pós-condição – o contrário da pré. Consegue ver? A pós-condição, ela nunca pode afrouxar. Porque, pensa o seguinte, eu tenho um método que devolve um inteiro. E esse inteiro é de 1 a 100. Aí a classe filho sobrescreve o método, e passa a retornar de 1 a 200. Isso pode quebrar as classes clientes. Porque imagina só, o cliente está esperando um retorno de 1 a 100, e ele trata isso, que é o que ele espera. E a classe filho vem, e pode devolver mais valores. Ela devolve um 150, que a classe cliente não estava esperando. O código não vai funcionar de maneira ideal.

Então, veja só que, para usar herança de maneira decente, eu tenho que pensar muito nas pré-condições e muito nas pós-condições. Eu nunca posso apertar uma pré-condição. E eu nunca posso afrouxar uma pós-condição. Veja que isso é bastante complicado, bem difícil de analisar na hora que você está desenvolvendo. Usar herança de maneira decente, criando classes filhas, que nunca vão quebrar quando elas entrarem numa referência que recebe a classe pai, a abstração.

Pra você programar desse jeito, você tem que pensar nessas coisas. Isso não é lá tão trivial. É bem complicado, na verdade.

É até por isso que muita gente fala “Olha, em vez de usar herança, favoreça a composição. Faça sua classe depender de outra classe, faça sua outra classe depender, talvez, dessa mesma classe, mas fuja de reaproveitar código por herança”.

Justamente por isso. Certo? Com a composição, você não tem muito esse problema, porque a classe Retangulo e a classe Quadrado são classes diferentes. E aí elas têm pré-condições que são diferentes, e não tem problema. A partir do momento em que você usa herança, o filho tem que conhecer as pré e pós-condições do pai. Isso dificulta mais. Quando eu favoreço a composição, eu tenho menos esse problema.

Então, vamos lá. Eu tenho a minha ContaDeEstudante, que é filho de ContaComum, e o método rende lança uma exceção. A ContaComum, bem parecida com aquela que eu mostrei no slide, onde o método rende faz uma conta qualquer. Uma conta qualquer aqui, uma exceção aqui, perceba que um quebrou a pré-condição do outro, porque o método pai não lança nenhuma exceção, o método filho passou a lançar. Não faz sentido. Problema de herança, vamos refatorar isso.

Nesse caso aqui, em particular, eu não vou refatorar e melhorar a herança que está feita. Mas eu vou fazer uso de composição. Porque, veja só, nesse contexto em particular, ContaComum tem de semelhante com ContaDeEstudante com saldo. Mais nada do que isso. Ambas têm operações que acontecem em cima do saldo.

O que eu vou fazer aqui é criar uma classe, que eu vou chamar de ManipuladorDeSaldo. Essa classe vai ter – e eu vou até aproveitar aqui e copiar o código da classe ContaComum ? vai ter um saldo. Vou começar como private, veja só que ele até estava como protected pro filho enxergar:


public class ManipuladorDeSaldo {    

    private double saldo;
}
Olha só, a gente já está afrouxando um pouquinho o encapsulamento, né, porque se você parar pra pensar, existe encapsulamento até quando você pensa em herança. Porque você tem que esconder da sua classe usando private. A hora que você coloca protected, você está deixando o filho mexer na classe pai, no atributo que o pai declarou. Você não sabe se as mudanças que o pai fizer vão bater com as mudanças que o filho vai fazer também. Então, é complicado usar o protected, você está afrouxando o encapsulamento, está deixando o filho mexer na classe pai, às vezes sem poder. Perigoso!

Então vou jogar fora daqui (o protected double saldo). O método deposita, saca, e getSaldo, eu vou levar pra lá, para o ManipuladorDeSaldo.

```java
public class ManipuladorDeSaldo  {

    private double saldo;

    public void deposita(double valor) {
        this.saldo += valor;
     }

    public void saca(double valor) {
        if (valor <= this.saldo)  {
            this.saldo -= valor;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double getSaldo()  {
        return saldo;
    }
}
```

Veio para cá. Tudo compilando. Aqui também eu vou criar o método rende. Vou até passar aqui um double taxa, que vai fazer

```java
public void rende(double taxa)  { 
    this.saldo *= taxa;
}
```

Ótimo. O ContaComum, então, não tem mais esse negócio de saldo 0. Ele vai fazer private ManipuladorDeSaldo, e vou chamar de manipulador. No construtor, ele vai dar o new nessa classe:

```java
public class ContaComum  {

    private     ManipuladorDeSaldo manipulador
    public ContaComum()  {
        this.manipulador = new ManipuladorDeSaldo();
}
```
Legal. O método rende, ele não vai mais implementar na unha. Ele vai passar pro manipulador. this.manipulador.rende(1.1), por exemplo, 10%.
```java
    public void rende()  { 
        this.manipulador.rende(1.1);
    }
```
Ah, mas a classe ContaComum tem um saque! Então, vamos lá:
```java
    public void saca(double valor)  {
        manipulador.saca(valor);
    }
```

O que ele vai fazer? manipulador.saca passando um valor.

Ah, mas aqui eu estou só repassando o método de uma classe pra outra! Não tem problema! Se amanhã aparecer uma outra regra de negócio, você pode por aqui:

```java
    public void saca(double valor)  {
        manipulador.saca(valor - 100);
    } 
```
O saque desconta 100 reais a menos, coisa e tal. Estou repassando para o manipulador. Não tem problema, às vezes é assim que acontece quando fazemos composição.

Ah, ele tem deposita também:

```java
    public void deposita(double valor)  {
        manipulador.deposita(valor);
    }
```
Ótimo. A ContaDeEstudante, eu poderia fazer a mesma coisa. Deixa eu tirar o deposita, o getMilhas. Aliás até posso deixar, certo, vou deixar o milhas. Vou só tirar o super.deposita aqui, porque aqui agora eu vou ter que começar a ter um ManipuladorDeSaldo também. Vou chamar de m, escrever um pouco menos, m é um péssimo nome de variável. m é um new ManipuladorDeSaldo. No deposita aqui, eu faço m.deposita e eu passo o valor.

```java
public class ContaDeEstudante extends ContaComum  {
    private ManipuladorDeSaldo m;
    private int milhas;

    public ContaDeEstudante()  { 
        m = new ManipuladorDeSaldo()
    }
    public void deposita(double valor)  {
        m.deposita(valor);
        this.milhas += (int)valor;
    }
    public int getMilhas()  {
        return.milhas;
    }
}
```

E o método rende, nem preciso dele. Antes eu tinha essa exceção sendo lançada, porque eu tinha uma classe pai, que eu vou até tirar daqui, extends ContaComum. Não tem mais essa herança acontecendo. Então, não preciso mais do método rende na classe ContaDeEstudante.

Veja só, o que eu fiz? Eu criei – esse ProcessadorDeInvestimentos para de compilar, porque não tenho o getSaldo. Não implementei aqui, mas poderia implementar, certo:

```java
    public double getSaldo()  {
        return manipulador.getSaldo();
    }
```

Então, vamos lá. Veja só o que eu fiz. ContaComum depende de ManipuladorDeSaldo. E ele repassa as chamadas, os métodos aqui, o saca, o deposita, o rende, repassa as chamadas pro manipulador.

Nesse código em particular, parece que eu só estou repassando chamadinha de uma classe pra outra. Mas num mundo mais complicado, eu poderia ter regras de negócio aqui, certo? Regra de negócio dos saques, específicos da conta comum aqui.

A mesma coisa na ContaDeEstudante. Aqui no deposita, veja só, eu tenho uma regra em particular. Porque eu faço um depósito no manipulador, e aí eu somo as milhas, porque conta de estudante tem milhas, por exemplo.

E o ManipuladorDeSaldo abstraiu ali o que as duas classes tinham em comum, que era manipular um saldo. Está legal? Então é assim que eu refatorei e tirei a herança, e coloquei composição.

Legal. Na refatoração, vocês viram que eu fiz uso ali de composição, fugi um pouco da herança. Tá certo? Relembra tudo o que eu falei nessa aula: Princípio de Liskov, toda classe filho tem que pensar nas pré-condições e pós-condições do pai, e ela nunca pode quebrar. Na pré-condição, ela nunca pode apertar. E na pós-condição, ela nunca pode afrouxar. Se não, as referências que apontam pra classe pai, quando receberem uma classe filho, não vão funcionar da maneira esperada.

Herança, composição, duas maneiras de reutilizar código. Você tem que optar. As pessoas falam muito “Olha, nunca use herança!”. Eu não sou tão extremista assim. Herança é legal e faz sentido em muitos casos.

Mas o ponto é só que ela é mais difícil de ser usada do que composição. Então, não descarte herança, mas favoreça a composição. Acho que essa é a principal lição dessa aula: é perceber a diferença entre essas duas maneiras de reaproveitar código. Quando usar herança de maneira decente, e como usar composição. Nessa aula é isso! Obrigado!.