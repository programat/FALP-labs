% file-predicates.pl

% New built_in predicates i'm using in this code:
% open/3, read_line_to_string/2, setup_call_cleanup/3, at_end_of_stream/1

% setup_call_cleanup(+Setup:callable, +Goal:callable, +Cleanup:callable)
% Executes Setup, then Goal, and then Cleanup, regardless of whether Goal succeeds or fails.
% Setup is a callable term that is executed first.
% Goal is a callable term that is executed after Setup.
% Cleanup is a callable term that is executed after Goal, regardless of whether Goal succeeds or fails.

% at_end_of_stream(+Stream:stream)
% Checks if the end of Stream has been reached.
% Stream is the stream to check.
% Returns true if the end of Stream has been reached, false otherwise.

% read_lines(+File:string, -Lines:list)
% Reads lines from a file into a list of strings.
read_lines(File, Lines) :-
    setup_call_cleanup(
        open(File, read, Stream),
        read_lines_helper(Stream, Lines),
        close(Stream)
    ).

% read_lines_helper(+Stream:stream, -Lines:list)
% Helper predicate to read lines from a stream into a list of strings.
read_lines_helper(Stream, []) :-
    at_end_of_stream(Stream).
read_lines_helper(Stream, [Line|Lines]) :-
    \+ at_end_of_stream(Stream),
    read_line_to_string(Stream, Line),
    read_lines_helper(Stream, Lines).


% task 2.1 --- length of the longest string

% max_length(+Lines:list, -MaxLen:integer)
% Finds the length of the longest string in a list of strings.
max_length(Lines, MaxLen) :-
    maplist(string_length, Lines, Lengths),
    max_list(Lengths, MaxLen).

% my_max_length(+Lines:list, -MaxLen:integer)
% Finds the length of the longest string in a list of strings using an accumulator.
my_max_length(Lines, MaxLen) :-
    my_max_length(Lines, 0, MaxLen).

% my_max_length(+Lines:list, +Acc:integer, -MaxLen:integer)
% Helper predicate for my_max_length/2.
my_max_length([], MaxLen, MaxLen).
my_max_length([Line|Lines], Acc, MaxLen) :-
    my_string_length(Line, Len),
    NewAcc is max(Acc, Len),
    my_max_length(Lines, NewAcc, MaxLen).

% my_string_length(+String:string, -Length:integer)
% Finds the length of a string.
my_string_length(String, Length) :-
    string_codes(String, Codes),
    length(Codes, Length).

% print_max_length(+MaxLen:integer)
% Prints the length of the longest string.
print_max_length(MaxLen) :-
    format("Length of the longest string ~w~n", [MaxLen]).


% task 2.2 --- number of strings without spaces

% hasnt_spaces(+Line:string)
% Checks if a string does not contain spaces.
hasnt_spaces(Line) :-
    \+ sub_string(Line, _, _, _, " ").

% count_lines_without_spaces(+Lines:list, -Count:integer)
% Counts the number of lines that do not contain spaces.
count_lines_without_spaces(Lines, Count) :-
    include(hasnt_spaces, Lines, LinesWithSpaces),
    length(LinesWithSpaces, Count).

% print_count_lines_without_spaces(+Count:integer)
% Prints the number of lines that do not contain spaces.
print_count_lines_without_spaces(Count) :-
    format("Number of strings without spaces: ~w~n", [Count]).


% task 2.3 --- average number of 'A' characters per line and lines with more 'A' characters than the average

% count_a(+Line:string, -Count:integer)
% Counts the number of 'A' characters in a string.
count_a(Line, Count) :-
    atom_chars(Line, Chars),
    include(=('A'), Chars, AChars),
    length(AChars, Count).

% average_a_count(+Lines:list, -Average:float)
% Calculates the average number of 'A' characters per line.
average_a_count(Lines, Average) :-
    maplist(count_a, Lines, Counts),
    sum_list(Counts, Sum),
    length(Lines, N),
    Average is Sum / N.

% filter_lines_above_average(+Lines:list, +Average:float, -Filtered:list)
% Filters lines that have more 'A' characters than the average.
filter_lines_above_average(Lines, Average, Filtered) :-
    include(count_a_above_average(Average), Lines, Filtered).

% count_a_above_average(+Average:float, +Line:string)
% Checks if a line has more 'A' characters than the average.
count_a_above_average(Average, Line) :-
    count_a(Line, Count),
    Count > Average.

% print_lines(+Lines:list)
% Prints a list of lines.
print_lines(Lines) :-
    maplist(writeln, Lines).


% task 2.4 --- most frequent word and lines with unique words

% split_lines_to_words(+Lines:list, -Words:list)
% Splits lines into words.
split_lines_to_words(Lines, Words) :-
    maplist(split_line_to_words, Lines, WordsList),
    append(WordsList, Words).

% split_line_to_words(+Line:string, -Words:list)
% Splits a line into words.
split_line_to_words(Line, Words) :-
    split_string(Line, " ", "", Words).

% group_consecutive_duplicates(+List:list, -Grouped:list)
% Groups consecutive duplicate elements in a list.
group_consecutive_duplicates([], []).
group_consecutive_duplicates([X], [X-1]).
group_consecutive_duplicates([X,Y|Rest], [X-Count|Groups]) :-
    X = Y,
    group_consecutive_duplicates([Y|Rest], [Y-CountRest|Groups]),
    Count is CountRest + 1.
group_consecutive_duplicates([X,Y|Rest], [X-1|Groups]) :-
    X \= Y,
    group_consecutive_duplicates([Y|Rest], Groups).

% most_frequent_word(+Words:list, -MostFrequentWord:string)
% Finds the most frequently occurring word in a list of words.
most_frequent_word(Words, MostFrequentWord) :-
    aggregate_all(max(Count, Word), (
        member(Word, Words),
        aggregate_all(count, member(Word, Words), Count)
    ), max(_, MostFrequentWord)).

% print_most_frequent_word(+Word:string)
% Prints the most frequent word.
print_most_frequent_word(Word) :-
    format("The most frequent word: ~w~n", [Word]).


% task 2.5 --- unique words and lines with unique words

% unique_words(+Words:list, -UniqueWords:list)
% Finds unique words in a list of words.
unique_words(Words, UniqueWords) :-
    findall(Word, (member(Word, Words), aggregate_all(count, member(Word, Words), 1)), UniqueWords).

% filter_unique_word_lines(+Lines:list, +UniqueWords:list, -FilteredLines:list)
% Filters lines that consist only of unique words.
filter_unique_word_lines(Lines, UniqueWords, FilteredLines) :-
    include(line_with_unique_words(UniqueWords), Lines, FilteredLines).

% line_with_unique_words(+UniqueWords:list, +Line:string)
% Checks if a line consists only of unique words.
line_with_unique_words(UniqueWords, Line) :-
    split_line_to_words(Line, Words),
    subset(Words, UniqueWords).

% write_lines(+File:string, +Lines:list)
% Writes lines to a file.
write_lines(File, Lines) :-
    setup_call_cleanup(
        open(File, write, Stream),
        (   maplist(format(Stream, "~s~n"), Lines)
        ),
        close(Stream)
    ).

% main
% Main predicate to read lines from a file, process them, and write results to another file.
main :-
    read_lines('data/test.txt', Lines),
    my_max_length(Lines, MaxLen),
    print_max_length(MaxLen),

    count_lines_without_spaces(Lines, Count),
    print_count_lines_without_spaces(Count),

    average_a_count(Lines, Average),
    format("Average number of 'A's: ~w~n", [Average]),
    filter_lines_above_average(Lines, Average, Filtered),
    print_lines(Filtered),

    split_lines_to_words(Lines, Words),
    most_frequent_word(Words, MostFrequentWord),
    print_most_frequent_word(MostFrequentWord),

    unique_words(Words, UniqueWords),
    writeln('Unique words: '), writeln(UniqueWords),
    filter_unique_word_lines(Lines, UniqueWords, FilteredLines),
    writeln('Filtered lines with unique words: '), writeln(FilteredLines),
    write_lines('data/output.txt', FilteredLines).
