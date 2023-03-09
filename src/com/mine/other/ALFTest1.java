package com.mine.other;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author CaoYang
 * @create 2023-03-09-12:34 PM
 * @description 某公司笔试题
 * "Order{ long id; String shop; BigDecimal value};
 * List<Order> orderList;有下列内容：
 * {1,""shopA"",10},{2,""shopB"",10},{3,""shopB"",20},{4,""shopC"",10},{5,""shopC"",20},{6,""shopC"",30}，{7,""shopC"",null}.
 * 1) 对orderList 按shop分组；
 * 2) 求各个shop的value之和；
 * 3) 求各个shop的value的平均值，保留2位小数
 * 4)求各个shop的value最大、最小值
 * 考虑真实情况下，可能有的order的属性是null，这种order不作处理，
 * 尽量使用最简的运算复杂度，循环遍历越少越好"
 */
public class ALFTest1 {
    private static List<Order> orderList = new ArrayList<>();
    static {
        Order order1 = new Order(1, "shopA", new BigDecimal(10));
        Order order2 = new Order(2, "shopB", new BigDecimal(10));
        Order order3 = new Order(3, "shopB", new BigDecimal(20));
        Order order4 = new Order(4, "shopC", new BigDecimal(10));
        Order order5 = new Order(5, "shopC", new BigDecimal(20));
        Order order6 = new Order(6, "shopC", new BigDecimal(30));
        Order order7 = new Order(7, "shopC", null);

        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
        orderList.add(order4);
        orderList.add(order5);
        orderList.add(order6);
        orderList.add(order7);
    }

    public static void main(String[] args) {
        // 1.问题1：
        System.out.println("问题1：");
        Map<String, List<Order>> map1 = func1();
        for (Map.Entry<String, List<Order> > entry : map1.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            System.out.println(entry.getValue());;
        }
        System.out.println("*******************************************");
        // 2.问题2：
        System.out.println("问题2：");
        Map<String, BigDecimal> map2 = func2(map1);
        System.out.println(map2);
        System.out.println("*******************************************");
        // 3. 问题3：
        System.out.println("问题3：");
        Map<String, BigDecimal> map3 = func3(map1, map2);
        System.out.println(map3);
        System.out.println("*******************************************");
        // 问题4：
        System.out.println("问题4：");
        Map<String, BigDecimal[]> map4 = func4(map1);
        for (Map.Entry<String, BigDecimal[]> entry : map4.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            System.out.println(Arrays.toString(entry.getValue()));
        }
        System.out.println("*******************************************");
    }

    public static Map<String, List<Order> > func1() {
        Map<String, List<Order> > res = new HashMap<>();
        if (orderList == null || orderList.size() == 0) {
            return res;
        }

        for (Order order : orderList) {
            if (order.shop != null) {
                List<Order> orders = res.get(order.shop);
                orders = orders == null ? new ArrayList<>() : orders;
                orders.add(order);
                res.put(order.shop, orders);
            }
        }

        return res;
    }

    public static Map<String, BigDecimal> func2(Map<String, List<Order> > map) {
        Map<String, BigDecimal> res = new HashMap<>();
        if (map == null || map.size() == 0) {
            return res;
        }
        for (Map.Entry<String, List<Order> > entry : map.entrySet()) {
            BigDecimal sum = new BigDecimal(0);
            for (Order order : entry.getValue()) {
                if (order.value != null) {
                    sum = sum.add(order.value);
                }
            }
            res.put(entry.getKey(), sum);
        }
        return res;
    }

    public static Map<String, BigDecimal> func3(Map<String, List<Order> > map1, Map<String, BigDecimal> map2) {
        Map<String, BigDecimal> res = new HashMap<>();
        if (map1 == null || map2 == null || map1.size() == 0 || map2.size() == 0) {
            return res;
        }
        for (Map.Entry<String, BigDecimal> entry : map2.entrySet()) {
            BigDecimal value = entry.getValue().multiply(new BigDecimal(1.00)).divide(new BigDecimal(map1.get(entry.getKey()).size()));
            DecimalFormat df1 = new DecimalFormat("0.00");
            String val = df1.format(value);
            res.put(entry.getKey(), new BigDecimal(val));
        }
        return res;
    }

    public static Map<String, BigDecimal[]> func4(Map<String, List<Order> > map) {
        Map<String, BigDecimal[]> res = new HashMap<>();
        if (map == null || map.size() == 0) {
            return res;
        }
        for (Map.Entry<String, List<Order> > entry : map.entrySet()) {
            BigDecimal min = new BigDecimal(Integer.MAX_VALUE);
            BigDecimal max = new BigDecimal(Integer.MIN_VALUE);
            for (Order order : entry.getValue()) {
                if (order.value != null) {
                    if (min.compareTo(order.value) > 0) {
                        min = order.value;
                    }

                    if (max.compareTo(order.value) < 0) {
                        max = order.value;
                    }
                }
            }
            res.put(entry.getKey(), new BigDecimal[]{min, max});
        }

        return res;
    }
}

class Order {
    long id;
    String shop;
    BigDecimal value;

    public Order(long id, String shop, BigDecimal value) {
        this.id = id;
        this.shop = shop;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", shop='" + shop + '\'' +
                ", value=" + value +
                '}';
    }
}
