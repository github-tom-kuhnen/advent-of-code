package y2024.day5

object Model {
  case class PageOrderingRule(first: Int, second: Int)
  object PageOrderingRule {
    def apply(input: String): PageOrderingRule = {
      val Array(first, second) = input.split('|').map(_.toInt)
      PageOrderingRule(first, second)
    }
  }
  case class PageUpdate(pages: List[Int]) {
    val middlePage: Int = pages(pages.length / 2)


    def sortPagesUpdate(orderedPages: List[Int]): PageUpdate = {
      val sortedPages = pages.sortBy {
        page =>
          orderedPages.indexOf(page)
      }
      PageUpdate(sortedPages)
    }
  }
  object PageUpdate {
    def apply(input: String): PageUpdate = PageUpdate(input.split(',').map(_.toInt).toList)
  }
  case class SafetyManual(orderingRules: List[PageOrderingRule], updates: List[PageUpdate]) {
    val safetyUpdates: List[PageUpdate] = {
      updates.filter {
        update =>
          val concernedRules = orderingRules.filter {
            rule =>
              update.pages.contains(rule.first) || update.pages.contains(rule.second)
          }
          concernedRules.forall {
            rule =>
              if (update.pages.contains(rule.first) && update.pages.contains(rule.second))
                update.pages.indexOf(rule.first) < update.pages.indexOf(rule.second)
              else true
          }
      }
    }

    val nonSafetyUpdates = updates.diff(safetyUpdates)


    def orderNonSafetyUpdates: List[PageUpdate] = {
      val orderedNonSafetyUpdates = nonSafetyUpdates.sortBy(_.middlePage)

      orderedNonSafetyUpdates.map {
        update =>
          val concernedRules: List[PageOrderingRule] = orderingRules.filter {
            rule => update.pages.contains(rule.first) || update.pages.contains(rule.second)
          }

          val sortedPagesFromRules: List[Int] = concernedRules.flatMap {
            rule =>
              if (update.pages.contains(rule.first) && update.pages.contains(rule.second))
                List(rule.first, rule.second)
              else if (update.pages.contains(rule.first))
                List(rule.first)
              else
                List(rule.second)
          }

          update.sortPagesUpdate(sortedPagesFromRules)
      }

      orderedNonSafetyUpdates
    }

    val totalMiddleSafetyPages: Int = safetyUpdates.map(_.middlePage).sum
  }

  object SafetyManual {

    def apply(input: String): SafetyManual = {
      val lines = input.split("\n")
      val firstBloc = lines.takeWhile(_.nonEmpty)
      val secondBloc = lines.dropWhile(_.nonEmpty)
      val rules = firstBloc.map(PageOrderingRule.apply).toList
      val updates = secondBloc.tail.map(PageUpdate.apply).toList
      SafetyManual(rules, updates)
    }
  }
}
