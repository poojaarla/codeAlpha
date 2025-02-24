import java.util.*;

 class Stock{
    String symbol;
    String name;
    double price;

    public Stock(String symbol, String name, double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " (" + symbol + "): $" + price;
    }
}

class Portfolio {
    Map<String, Integer> holdings;
    double balance;

    public Portfolio(double initialBalance) {
        this.holdings = new HashMap<>();
        this.balance = initialBalance;
    }

    public void buyStock(Stock stock, int quantity) {
        double cost = stock.getPrice() * quantity;
        if (balance >= cost) {
            holdings.put(stock.getSymbol(), holdings.getOrDefault(stock.getSymbol(), 0) + quantity);
            balance -= cost;
            System.out.println("Bought " + quantity + " shares of " + stock.getName() + " for $" + cost);
        } else {
            System.out.println("Insufficient funds to buy " + quantity + " shares of " + stock.getName());
        }
    }

    public void sellStock(Stock stock, int quantity) {
        if (holdings.containsKey(stock.getSymbol()) && holdings.get(stock.getSymbol()) >= quantity) {
            double revenue = stock.getPrice() * quantity;
            holdings.put(stock.getSymbol(), holdings.get(stock.getSymbol()) - quantity);
            balance += revenue;
            System.out.println("Sold " + quantity + " shares of " + stock.getName() + " for $" + revenue);
        } else {
            System.out.println("Insufficient shares of " + stock.getName() + " to sell.");
        }
    }

    public double getPortfolioValue(Map<String, Stock> marketData) {
        double portfolioValue = balance;
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            String symbol = entry.getKey();
            int quantity = entry.getValue();
            if (marketData.containsKey(symbol)) {
                portfolioValue += marketData.get(symbol).getPrice() * quantity;
            }
        }
        return portfolioValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Portfolio:\n");
        sb.append("Balance: $").append(balance).append("\n");
        sb.append("Holdings:\n");
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            sb.append(entry.getValue()).append(" shares of ").append(entry.getKey()).append("\n");
        }
        return sb.toString();
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {
        Map<String, Stock> marketData = new HashMap<>();
        marketData.put("AAPL", new Stock("AAPL", "Apple Inc.", 150.0));
        marketData.put("GOOG", new Stock("GOOG", "Alphabet Inc.", 2500.0));
        marketData.put("MSFT", new Stock("MSFT", "Microsoft Corp.", 300.0));

        Portfolio portfolio = new Portfolio(10000.0);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Stock Trading Platform ---");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("\n--- Market Data ---");
                    for (Stock stock : marketData.values()) {
                        System.out.println(stock);
                    }
                    break;
                case 2:
                    System.out.print("Enter stock symbol to buy: ");
                    String buySymbol = scanner.nextLine().toUpperCase();
                    if (marketData.containsKey(buySymbol)) {
                        System.out.print("Enter quantity to buy: ");
                        int buyQuantity = scanner.nextInt();
                        portfolio.buyStock(marketData.get(buySymbol), buyQuantity);
                    } else {
                        System.out.println("Invalid stock symbol.");
                    }
                    break;
                case 3:
                    System.out.print("Enter stock symbol to sell: ");
                    String sellSymbol = scanner.nextLine().toUpperCase();
                    if (marketData.containsKey(sellSymbol)) {
                        System.out.print("Enter quantity to sell: ");
                        int sellQuantity = scanner.nextInt();
                        portfolio.sellStock(marketData.get(sellSymbol), sellQuantity);
                    } else {
                        System.out.println("Invalid stock symbol.");
                    }
                    break;
                case 4:
                    System.out.println("\n--- Portfolio ---");
                    System.out.println(portfolio);
                    System.out.println("Portfolio Value: $" + portfolio.getPortfolioValue(marketData));
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}