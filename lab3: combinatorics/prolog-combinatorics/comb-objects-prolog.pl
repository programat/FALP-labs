% comb-objects-prolog.pl

% generate_arrangements_with_repetition(+K:integer, +Alphabet:list, -Arrangement:list)
% Generates arrangements with repetition of length K from the given Alphabet.
generate_arrangements_with_repetition(0, _, []).
generate_arrangements_with_repetition(K, Alphabet, [X|T]) :-
   K > 0,
   member(X, Alphabet),
   K1 is K - 1,
   generate_arrangements_with_repetition(K1, Alphabet, T).

% print_list(+List:list)
% Prints the elements of the list.
print_list([]).
print_list([X|T]) :- write(X), write(' '), print_list(T).

% arrangements_with_repetition(+K:integer, +Alphabet:list)
% Generates and prints all arrangements with repetition of length K from the given Alphabet.
arrangements_with_repetition(K, Alphabet) :-
   arrangements_with_repetition(K, Alphabet, Res),
   print_list(Res), nl, fail.

% generate_combinations_without_repetition(+K:integer, +Alphabet:list, -Combination:list)
% Generates combinations without repetition of length K from the given Alphabet.
generate_combinations_without_repetition(0, _, []) :- !.
generate_combinations_without_repetition(K, [El|RestAlph], [El|Comb]) :-
    Knew is K - 1,
    generate_combinations_without_repetition(Knew, RestAlph, Comb).
generate_combinations_without_repetition(K, [El|RestAlph], Comb) :-
    generate_combinations_without_repetition(K, RestAlph, Comb).

% combinations_without_repetition(+K:integer, +Alphabet:list)
% Generates and prints all combinations without repetition of length K from the given Alphabet.
combinations_without_repetition(K, Alphabet) :-
    generate_combinations_without_repetition(K, Alphabet, Res),
    print_list(Res), nl, fail.

% generate_arrangements_with_repetition_nonrec(+K:integer, +Alphabet:list, -Arrangement:list)
% Generates arrangements with repetition of length K from the given Alphabet using a non-recursive approach.
generate_arrangements_with_repetition_nonrec(K, Alphabet, A) :-
   length(A, K),
   maplist(member_of(L), A).

% arrangements_with_repetition_nonrec(+K:integer, +Alphabet:list)
% Generates and prints all arrangements with repetition of length K from the given Alphabet using a non-recursive approach.
arrangements_with_repetition_nonrec(K, Alphabet) :-
    generate_arrangements_with_repetition(K, Alphabet, A),
    print_list(A), nl, fail.

% member_of(+List:list, -Element:term)
% True if Element is a member of List.
member_of(L, X) :- member(X, L).

% combinations_without_repetition_nonrec(+K:integer, +List:list, -Combinations:list)
% Generates all combinations without repetition of length K from the given List using a non-recursive approach.
combinations_without_repetition_nonrec(K, L, Combinations) :-
    findall(C, (subset(L, C), length(C, K)), Combinations).

% subset(+List:list, -Subset:list)
% Generates all subsets of the given List.
subset([], []).
subset([E|Tail], [E|NTail]):-
    subset(Tail, NTail).
subset([_|Tail], NTail):-
    subset(Tail, NTail).

% words_with_3_a(+K:integer, +Alphabet:list, -Word:list)
% Generates all words of length K containing exactly 3 occurrences of the letter 'a' from the given Alphabet.
words_with_3_a(K, L, W) :-
   length(W, K),
   append(_, ['a'|T1], W),
   append(_, ['a'|T2], T1),
   append(_, ['a'|_], T2),
   subtract(W, ['a','a','a'], R),
   maplist(member_of(L), R).

% make_pos_list(+K:integer, +CurPos:integer, -PosList:list)
% Generates a list of positions from CurPos to K.
make_pos_list(K, K, []) :- !.
make_pos_list(K, CurPos, [NewPos|TailPos]) :- NewPos is CurPos + 1, make_pos_list(K, NewPos, TailPos).

% make_3a_empty_word(+K:integer, +CurIndex:integer, +PosList:list, -WordEmpty3a:list)
% Generates an empty word of length K with 3 occurrences of 'a' at the positions specified in PosList.
make_3a_empty_word(K, K, _, []) :- !.
make_3a_empty_word(K, CurIndex, [NewIndex|PosTail], [a|Tail]) :-
    NewIndex is CurIndex + 1, make_3a_empty_word(K, NewIndex, PosTail, Tail), !.
make_3a_empty_word(K, CurIndex, PosList, [_|Tail]) :-
    NewIndex is CurIndex + 1, make_3a_empty_word(K, NewIndex, PosList, Tail).

% build_word(+Word:list, +WordEmpty3a:list, +RestWord:list)
% Builds a word by filling the empty positions in WordEmpty3a with letters from RestWord.
build_word([], [], _) :- !.
build_word([a|WordTail], [X|WordEmpty3aTail], RestWord) :-
    nonvar(X), build_word(WordTail, WordEmpty3aTail, RestWord), !.
build_word([Y|WordTail], [X|WordEmpty3aTail], [Y|RestWordTail]) :-
    var(X), build_word(WordTail, WordEmpty3aTail, RestWordTail).

% build_3a_words_of_k(+Alphabet:list, +K:integer, -Word:list)
% Generates all words of length K containing exactly 3 occurrences of 'a' from the given Alphabet.
build_3a_words_of_k(Alphabet, K, Word) :-
    make_pos_list(K, 0, PosList),
    sochet(Pos_a_List, PosList, 3),
    make_3a_empty_word(K, 0, Pos_a_List, WordEmpty3a),
    Alphabet = [a|NewAlphabet],
    M is K - 3,
    razm_povt(NewAlphabet, M, [], RestWord),
    build_word(Word, WordEmpty3a, RestWord).
