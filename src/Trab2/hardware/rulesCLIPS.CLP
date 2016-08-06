(deftemplate Erro
(slot id (default ?NONE))
(slot msg (default ?NONE))
)

(deftemplate piece_inStation
(slot pos (default ?NONE))
)

(deftemplate elevator_atStation
(slot pos (default ?NONE))
)

(deftemplate piece_inElevator
(slot status (default ?NONE))
)

(deftemplate STATION_LOAD_DONE
(slot status (default ?NONE))
)

(defrule EntryReady_Rule
    (piece_inStation (pos ?b))
    (elevator_atStation (pos ?a))
    (test (neq ?b ?a))
    (test (or (eq ?a Left) (eq ?a Right)))
=> (assert (Erro (id 1) (msg Erro))))

(defrule EntryLoad_Rule
    (piece_inElevator (status ?a))
    (STATION_LOAD_DONE (status ?b))
    (test (eq ?a false))
    (test (eq ?b true))
=> (assert (Erro (id 2) (msg Erro))))
