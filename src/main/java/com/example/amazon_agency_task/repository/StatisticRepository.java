package com.example.amazon_agency_task.repository;

import com.example.amazon_agency_task.domain.ReportTypeAndOptions;
import com.example.amazon_agency_task.entity.Statistic;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticRepository extends MongoRepository<Statistic, String> {

    @Query(
            value = "{parentAsin: {$in: ?0}, reportTypeAndOptions: ?1}",
            fields = "{_id: 1, parentAsin: 1, reportTypeAndOptions: 1}"
    )
    List<Statistic> findByAsinAndOptions(List<String> asin, ReportTypeAndOptions options);

    @Query(
            value = "{date: {$in: ?0 }, reportTypeAndOptions: ?1}",
            fields = "{_id: 1, date: 1, reportTypeAndOptions: 1}"
    )
    List<Statistic> findByDateAndOptions(List<String> date, ReportTypeAndOptions options);

    List<Statistic> findByDateBetween(String startDate, String endDate);
    List<Statistic> findByDate(String date);

    @Query(value = "{'parentAsin': { $in: ?0 } }")
    List<Statistic> findByAsinIn(List<String> asins);

    @Aggregation(pipeline = {
                    "  { $match: {" +
                    "      date: { $ne: null }" +
                    "    }" +
                    "  }",
                    "  { $group: {" +
                    "      _id:  ''," +
                    "      orderedProductSales: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "             '$salesByDate.orderedProductSales.amount'" +
                    "        }" +
                    "      }," +
                    "      orderedProductSalesCurrency: {" +
                    "        $first:" +
                    "           '$salesByDate.orderedProductSales.currencyCode'" +
                    "      }," +
                    "      orderedProductSalesB2B: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "             '$salesByDate.orderedProductSalesB2B.amount'" +
                    "        }" +
                    "      }," +
                    "      orderedProductSalesB2BCurrency: {" +
                    "        $first:" +
                    "           '$salesByDate.orderedProductSalesB2B.currencyCode'" +
                    "      }," +
                    "      unitsOrdered: {" +
                    "        $sum: {" +
                    "          $toDouble:  '$salesByDate.unitsOrdered'" +
                    "        }" +
                    "      }," +
                    "      unitsOrderedB2B: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "             '$salesByDate.unitsOrderedB2B'" +
                    "        }" +
                    "      }," +
                    "      totalOrderItems: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "             '$salesByDate.totalOrderItems'" +
                    "        }" +
                    "      }," +
                    "      totalOrderItemsB2B: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "             '$salesByDate.totalOrderItemsB2B'" +
                    "        }" +
                    "      }," +
                    "      averageSalesPerOrderItem: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "             '$salesByDate.averageSalesPerOrderItem.amount'" +
                    "        }" +
                    "      }," +
                    "      averageSalesPerOrderItemCurrency: {" +
                    "        $first:" +
                    "           '$salesByDate.averageSalesPerOrderItem.currencyCode'" +
                    "      }," +
                    "      averageSalesPerOrderItemB2B: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "             '$salesByDate.averageSalesPerOrderItemB2B.amount'" +
                    "        }" +
                    "      }," +
                    "      averageSalesPerOrderItemB2BCurrency: {" +
                    "        $first:" +
                    "           '$salesByDate.averageSalesPerOrderItemB2B.currencyCode'" +
                    "      }," +
                    "      averageUnitsPerOrderItem: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "             '$salesByDate.averageUnitsPerOrderItem'" +
                    "        }" +
                    "      }," +
                    "      averageUnitsPerOrderItemB2B: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "             '$salesByDate.averageUnitsPerOrderItemB2B'" +
                    "        }" +
                    "      }," +
                    "      averageSellingPrice: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "             '$salesByDate.averageSellingPrice.amount'" +
                    "        }" +
                    "      }," +
                    "      averageSellingPriceCurrency: {" +
                    "        $first:" +
                    "           '$salesByDate.averageSellingPrice.currencyCode'" +
                    "      }," +
                    "      averageSellingPriceB2B: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "             '$salesByDate.averageSellingPriceB2B.amount'" +
                    "        }" +
                    "      }," +
                    "      averageSellingPriceB2BCurrency: {" +
                    "        $first:" +
                    "           '$salesByDate.averageSellingPriceB2B.currencyCode'" +
                    "      }," +
                    "      unitsRefunded: {" +
                    "        $sum: {" +
                    "          $toDouble:  '$salesByDate.unitsRefunded'" +
                    "        }" +
                    "      }," +
                    "      refundRate: {" +
                    "        $sum: {" +
                    "          $toDouble:  '$salesByDate.refundRate'" +
                    "        }" +
                    "      }," +
                    "      claimsGranted: {" +
                    "        $sum: {" +
                    "          $toDouble:  '$salesByDate.claimsGranted'" +
                    "        }" +
                    "      }," +
                    "      claimsAmount: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "             '$salesByDate.claimsAmount.amount'" +
                    "        }" +
                    "      }," +
                    "      claimsAmountCurrency: {" +
                    "        $first:" +
                    "           '$salesByDate.claimsAmount.currencyCode'" +
                    "      }," +
                    "      shippedProductSales: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "             '$salesByDate.shippedProductSales.amount'" +
                    "        }" +
                    "      }," +
                    "      shippedProductSalesCurrency: {" +
                    "        $first:" +
                    "           '$salesByDate.shippedProductSales.currencyCode'" +
                    "      }," +
                    "      unitsShipped: {" +
                    "        $sum: {" +
                    "          $toDouble:  '$salesByDate.unitsShipped'" +
                    "        }" +
                    "      }," +
                    "      ordersShipped: {" +
                    "        $sum: {" +
                    "          $toDouble:  '$salesByDate.ordersShipped'" +
                    "        }" +
                    "      }," +
                    "      browserPageViews: {" +
                    "        $sum:  '$trafficByDate.browserPageViews'" +
                    "      }," +
                    "      browserPageViewsB2B: {" +
                    "        $sum:  '$trafficByDate.browserPageViewsB2B'" +
                    "      }," +
                    "      mobileAppPageViews: {" +
                    "        $sum:  '$trafficByDate.mobileAppPageViews'" +
                    "      }," +
                    "      mobileAppPageViewsB2B: {" +
                    "        $sum:  '$trafficByDate.mobileAppPageViewsB2B'" +
                    "      }," +
                    "      pageViews: {" +
                    "        $sum:  '$trafficByDate.pageViews'" +
                    "      }," +
                    "      pageViewsB2B: {" +
                    "        $sum:  '$trafficByDate.pageViewsB2B'" +
                    "      }," +
                    "      browserSessions: {" +
                    "        $sum:  '$trafficByDate.browserSessions'" +
                    "      }," +
                    "      browserSessionsB2B: {" +
                    "        $sum:  '$trafficByDate.browserSessionsB2B'" +
                    "      }," +
                    "      mobileAppSessions: {" +
                    "        $sum:  '$trafficByDate.mobileAppSessions'" +
                    "      }," +
                    "      mobileAppSessionsB2B: {" +
                    "        $sum:  '$trafficByDate.mobileAppSessionsB2B'" +
                    "      }," +
                    "      sessions: {" +
                    "        $sum:  '$trafficByDate.sessions'" +
                    "      }," +
                    "      sessionsB2B: {" +
                    "        $sum:  '$trafficByDate.sessionsB2B'" +
                    "      }," +
                    "      buyBoxPercentage: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "             '$trafficByDate.buyBoxPercentage'" +
                    "        }" +
                    "      }," +
                    "      buyBoxPercentageB2B: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "             '$trafficByDate.buyBoxPercentageB2B'" +
                    "        }" +
                    "      }," +
                    "      orderItemSessionPercentage: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "             '$trafficByDate.orderItemSessionPercentage'" +
                    "        }" +
                    "      }," +
                    "      orderItemSessionPercentageB2B: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "             '$trafficByDate.orderItemSessionPercentageB2B'" +
                    "        }" +
                    "      }," +
                    "      unitSessionPercentage: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "             '$trafficByDate.unitSessionPercentage'" +
                    "        }" +
                    "      }," +
                    "      unitSessionPercentageB2B: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "             '$trafficByDate.unitSessionPercentageB2B'" +
                    "        }" +
                    "      }," +
                    "      averageOfferCount: {" +
                    "        $sum:  '$trafficByDate.averageOfferCount'" +
                    "      }," +
                    "      averageParentItems: {" +
                    "        $sum:  '$trafficByDate.averageParentItems'" +
                    "      }," +
                    "      feedbackReceived: {" +
                    "        $sum:  '$trafficByDate.feedbackReceived'" +
                    "      }," +
                    "      negativeFeedbackReceived: {" +
                    "        $sum:  '$trafficByDate.negativeFeedbackReceived'" +
                    "      }," +
                    "      receivedNegativeFeedbackRate: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "             '$trafficByDate.receivedNegativeFeedbackRate'" +
                    "        }" +
                    "      }" +
                    "    }" +
                    "  }",
                    "  { $project: {" +
                    "      _id:  '$_id'," +
                    "      salesByDate: {" +
                    "        orderedProductSales: {" +
                    "          amount:'$orderedProductSales'," +
                    "          currencyCode:'$orderedProductSalesCurrency'" +
                    "        }," +
                    "        orderedProductSalesB2B: {" +
                    "          amount:'$orderedProductSalesB2B'," +
                    "          currencyCode:'$orderedProductSalesB2BCurrency'" +
                    "        }," +
                    "        unitsOrdered:'$unitsOrdered'," +
                    "        unitsOrderedB2B:'$unitsOrderedB2B'," +
                    "        totalOrderItems:'$totalOrderItems'," +
                    "        totalOrderItemsB2B:'$totalOrderItemsB2B'," +
                    "        averageSalesPerOrderItem: {" +
                    "          amount:'$averageSalesPerOrderItem'," +
                    "          currencyCode:'$averageSalesPerOrderItemCurrency'" +
                    "        }," +
                    "        averageSalesPerOrderItemB2B: {" +
                    "          amount:'$averageSalesPerOrderItemB2B'," +
                    "          currencyCode:'$averageSalesPerOrderItemB2BCurrency'" +
                    "        }," +
                    "        averageUnitsPerOrderItem:'$averageUnitsPerOrderItem'," +
                    "        averageUnitsPerOrderItemB2B:'$averageUnitsPerOrderItemB2B'," +
                    "        averageSellingPrice: {" +
                    "          amount:'$averageSellingPrice'," +
                    "          currencyCode:'$averageSellingPriceCurrency'" +
                    "        }," +
                    "        averageSellingPriceB2B: {" +
                    "          amount:  '$averageSellingPriceB2B'," +
                    "          currencyCode:'$averageSellingPriceB2BCurrency'" +
                    "        }," +
                    "        unitsRefunded:'$unitsRefunded'," +
                    "        refundRate:'$refundRate'," +
                    "        claimsGranted:'$claimsGranted'," +
                    "        claimsAmount: {" +
                    "          amount:'$claimsAmount'," +
                    "          currencyCode:'$claimsAmountCurrency'" +
                    "        }," +
                    "        shippedProductSales: {" +
                    "          amount:  '$shippedProductSales'," +
                    "          currencyCode:'$shippedProductSalesCurrency'" +
                    "        }," +
                    "        unitsShipped:  '$unitsShipped'," +
                    "        ordersShipped:  '$ordersShipped'" +
                    "      }," +
                    "      trafficByDate: {" +
                    "        browserPageViews:'$browserPageViews'," +
                    "        browserPageViewsB2B:'$browserPageViewsB2B'," +
                    "        mobileAppPageViews:  '$mobileAppPageViews'," +
                    "        mobileAppPageViewsB2B:'$mobileAppPageViewsB2B'," +
                    "        pageViews:  '$pageViews'," +
                    "        pageViewsB2B:  '$pageViewsB2B'," +
                    "        browserSessions:  '$browserSessions'," +
                    "        browserSessionsB2B:  '$browserSessionsB2B'," +
                    "        mobileAppSessions:  '$mobileAppSessions'," +
                    "        mobileAppSessionsB2B:'$mobileAppSessionsB2B'," +
                    "        sessions:'$sessions'," +
                    "        sessionsB2B:'$sessionsB2B'," +
                    "        buyBoxPercentage:'$buyBoxPercentage'," +
                    "        buyBoxPercentageB2B:'$buyBoxPercentageB2B'," +
                    "        orderItemSessionPercentage:'$orderItemSessionPercentage'," +
                    "        orderItemSessionPercentageB2B:'$orderItemSessionPercentageB2B'," +
                    "        unitSessionPercentage:'$unitSessionPercentage'," +
                    "        unitSessionPercentageB2B:'$unitSessionPercentageB2B'," +
                    "        averageOfferCount:  '$averageOfferCount'," +
                    "        averageParentItems:  '$averageParentItems'," +
                    "        feedbackReceived:  '$feedbackReceived'," +
                    "        negativeFeedbackReceived:'$negativeFeedbackReceived'," +
                    "        receivedNegativeFeedbackRate:'$receivedNegativeFeedbackRate'" +
                    "      }" +
                    "    }" +
                    "  }"
    })
    Statistic aggregateStatisticByAllDates();

    @Aggregation(pipeline = {
                    "  {" +
                    "    $match: {" +
                    "      parentAsin: {" +
                    "        $ne: null" +
                    "      }" +
                    "    }" +
                    "  }",
                    "  {" +
                    "    $group: {" +
                    "      _id: ''," +
                    "      unitsOrdered: {" +
                    "        $sum: {" +
                    "          $toDouble: '$salesByAsin.unitsOrdered'" +
                    "        }" +
                    "      }," +
                    "      unitsOrderedB2B: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$salesByAsin.unitsOrderedB2B'" +
                    "        }" +
                    "      }," +
                    "      orderedProductSales: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$salesByAsin.orderedProductSales.amount'" +
                    "        }" +
                    "      }," +
                    "      orderedProductSalesCurrency: {" +
                    "        $first:" +
                    "          '$salesByAsin.orderedProductSales.currencyCode'" +
                    "      }," +
                    "      orderedProductSalesB2B: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$salesByAsin.orderedProductSalesB2B.amount'" +
                    "        }" +
                    "      }," +
                    "      orderedProductSalesB2BCurrency: {" +
                    "        $first:" +
                    "          '$salesByAsin.orderedProductSalesB2B.currencyCode'" +
                    "      }," +
                    "      totalOrderItems: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$salesByAsin.totalOrderItems'" +
                    "        }" +
                    "      }," +
                    "      totalOrderItemsB2B: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$salesByAsin.totalOrderItemsB2B'" +
                    "        }" +
                    "      }," +
                    "      browserSessions: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$trafficByAsin.browserSessions'" +
                    "        }" +
                    "      }," +
                    "      browserSessionsB2B: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$trafficByAsin.browserSessionsB2B'" +
                    "        }" +
                    "      }," +
                    "      mobileAppSessions: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$trafficByAsin.mobileAppSessions'" +
                    "        }" +
                    "      }," +
                    "      mobileAppSessionsB2B: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$trafficByAsin.mobileAppSessionsB2B'" +
                    "        }" +
                    "      }," +
                    "      sessions: {" +
                    "        $sum: {" +
                    "          $toDouble: '$trafficByAsin.sessions'" +
                    "        }" +
                    "      }," +
                    "      sessionsB2B: {" +
                    "        $sum: {" +
                    "          $toDouble: '$trafficByAsin.sessionsB2B'" +
                    "        }" +
                    "      }," +
                    "      browserSessionPercentage: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$trafficByAsin.browserSessionPercentage'" +
                    "        }" +
                    "      }," +
                    "      browserSessionPercentageB2B: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$trafficByAsin.browserSessionPercentageB2B'" +
                    "        }" +
                    "      }," +
                    "      mobileAppSessionPercentage: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$trafficByAsin.mobileAppSessionPercentage'" +
                    "        }" +
                    "      }," +
                    "      mobileAppSessionPercentageB2B: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$trafficByAsin.mobileAppSessionPercentageB2B'" +
                    "        }" +
                    "      }," +
                    "      sessionPercentage: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$trafficByAsin.sessionPercentage'" +
                    "        }" +
                    "      }," +
                    "      sessionPercentageB2B: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$trafficByAsin.sessionPercentageB2B'" +
                    "        }" +
                    "      }," +
                    "      browserPageViews: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$trafficByAsin.browserPageViews'" +
                    "        }" +
                    "      }," +
                    "      browserPageViewsB2B: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$trafficByAsin.browserPageViewsB2B'" +
                    "        }" +
                    "      }," +
                    "      mobileAppPageViews: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$trafficByAsin.mobileAppPageViews'" +
                    "        }" +
                    "      }," +
                    "      mobileAppPageViewsB2B: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$trafficByAsin.mobileAppPageViewsB2B'" +
                    "        }" +
                    "      }," +
                    "      pageViews: {" +
                    "        $sum: {" +
                    "          $toDouble: '$trafficByAsin.pageViews'" +
                    "        }" +
                    "      }," +
                    "      pageViewsB2B: {" +
                    "        $sum: {" +
                    "          $toDouble: '$trafficByAsin.pageViewsB2B'" +
                    "        }" +
                    "      }," +
                    "      browserPageViewsPercentage: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$trafficByAsin.browserPageViewsPercentage'" +
                    "        }" +
                    "      }," +
                    "      browserPageViewsPercentageB2B: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$trafficByAsin.browserPageViewsPercentageB2B'" +
                    "        }" +
                    "      }," +
                    "      mobileAppPageViewsPercentage: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$trafficByAsin.mobileAppPageViewsPercentage'" +
                    "        }" +
                    "      }," +
                    "      mobileAppPageViewsPercentageB2B: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$trafficByAsin.mobileAppPageViewsPercentageB2B'" +
                    "        }" +
                    "      }," +
                    "      pageViewsPercentage: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$trafficByAsin.pageViewsPercentage'" +
                    "        }" +
                    "      }," +
                    "      pageViewsPercentageB2B: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$trafficByAsin.pageViewsPercentageB2B'" +
                    "        }" +
                    "      }," +
                    "      buyBoxPercentage: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$trafficByAsin.buyBoxPercentage'" +
                    "        }" +
                    "      }," +
                    "      buyBoxPercentageB2B: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$trafficByAsin.buyBoxPercentageB2B'" +
                    "        }" +
                    "      }," +
                    "      unitSessionPercentage: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$trafficByAsin.unitSessionPercentage'" +
                    "        }" +
                    "      }," +
                    "      unitSessionPercentageB2B: {" +
                    "        $sum: {" +
                    "          $toDouble:" +
                    "            '$trafficByAsin.unitSessionPercentageB2B'" +
                    "        }" +
                    "      }" +
                    "    }" +
                    "  }",
                    "  {" +
                    "    $project: {" +
                    "      salesByAsin: {" +
                    "        unitsOrdered: '$unitsOrdered'," +
                    "        unitsOrderedB2B: '$unitsOrderedB2B'," +
                    "        orderedProductSales: {" +
                    "          amount: '$orderedProductSales'," +
                    "          currencyCode: '$orderedProductSalesCurrency'" +
                    "        }," +
                    "        orderedProductSalesB2B: {" +
                    "          amount: '$orderedProductSalesB2B'," +
                    "          currencyCode: '$orderedProductSalesB2BCurrency'" +
                    "        }," +
                    "        totalOrderItems: '$totalOrderItems'," +
                    "        totalOrderItemsB2B: '$totalOrderItemsB2B'" +
                    "      }," +
                    "      trafficByAsin: {" +
                    "        browserSessions: '$browserSessions'," +
                    "        browserSessionsB2B: '$browserSessionsB2B'," +
                    "        mobileAppSessions: '$mobileAppSessions'," +
                    "        mobileAppSessionsB2B: '$mobileAppSessionsB2B'," +
                    "        sessions: '$sessions'," +
                    "        sessionsB2B: '$sessionsB2B'," +
                    "        browserSessionPercentage: '$browserSessionPercentage'," +
                    "        browserSessionPercentageB2B: '$browserSessionPercentageB2B'," +
                    "        mobileAppSessionPercentage: '$mobileAppSessionPercentage'," +
                    "        mobileAppSessionPercentageB2B: '$mobileAppSessionPercentageB2B'," +
                    "        sessionPercentage: '$sessionPercentage'," +
                    "        sessionPercentageB2B: '$sessionPercentageB2B'," +
                    "        browserPageViews: '$browserPageViews'," +
                    "        browserPageViewsB2B: '$browserPageViewsB2B'," +
                    "        mobileAppPageViews: '$mobileAppPageViews'," +
                    "        mobileAppPageViewsB2B: '$mobileAppPageViewsB2B'," +
                    "        pageViews: '$pageViews'," +
                    "        pageViewsB2B: '$pageViewsB2B'," +
                    "        browserPageViewsPercentage: '$browserPageViewsPercentage'," +
                    "        browserPageViewsPercentageB2B: '$browserPageViewsPercentageB2B'," +
                    "        mobileAppPageViewsPercentage: '$mobileAppPageViewsPercentage'," +
                    "        mobileAppPageViewsPercentageB2B: '$mobileAppPageViewsPercentageB2B'," +
                    "        pageViewsPercentage: '$pageViewsPercentage'," +
                    "        pageViewsPercentageB2B: '$pageViewsPercentageB2B'," +
                    "        buyBoxPercentage: '$buyBoxPercentage'," +
                    "        buyBoxPercentageB2B: '$buyBoxPercentageB2B'," +
                    "        unitSessionPercentage: '$unitSessionPercentage'," +
                    "        unitSessionPercentageB2B: '$unitSessionPercentageB2B'" +
                    "      }" +
                    "    }" +
                    "  }"
    })
    Statistic aggregateStatisticByAllAsins();
}
