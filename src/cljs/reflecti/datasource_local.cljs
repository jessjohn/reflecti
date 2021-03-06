;;   Copyright (c) 7theta. All rights reserved.
;;   The use and distribution terms for this software are covered by the
;;   Eclipse Public License 1.0 (http://www.eclipse.org/legal/epl-v10.html)
;;   which can be found in the LICENSE file at the root of this
;;   distribution.
;;
;;   By using this software in any fashion, you are agreeing to be bound by
;;   the terms of this license.
;;   You must not remove this notice, or any others, from this software.

(ns reflecti.datasource-local
  (:require [reflecti.datasource :refer [data dispatch-sort
                                         DataSource Sortable]]
            [schema.core :as schema :include-macros true]
            [reagent.ratom :refer [RAtom]]))

(schema/defrecord LocalDataSource [data-ratom :- RAtom]
  DataSource
  (data [this] data-ratom)

  Sortable
  (dispatch-sort [this sort-key direction]
    (reset! data-ratom (cond->
                           (sort-by sort-key @data-ratom)
                         (= :desc direction)
                         reverse))))
