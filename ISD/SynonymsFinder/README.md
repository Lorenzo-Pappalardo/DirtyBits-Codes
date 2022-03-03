# Synonyms Finder

## Goal

Given two files each containing health and medicine terms together with their descriptions, in the form of key-value
records, create a program capable of calculating the [Jaccard Index](https://en.wikipedia.org/wiki/Jaccard_index) for
each couple of keys based on their descriptions. Considering each file as a set, the cardinality of one is way higher
than that of the other.

To calculate the Jaccard index the two term's descriptions should be compared using the following formula:

[![Jaccard Index formula](https://wikimedia.org/api/rest_v1/media/math/render/svg/eaef5aa86949f49e7dc6b9c8c3dd8b233332c9e7)](https://en.wikipedia.org/wiki/Jaccard_index)

given:

- A = Set of terms coming from the first file, called _base_
- B = Set of terms coming from the second file, called _dictionary_

The program should output a file that contains the two compared terms and the Jaccard Index resulting from the
application of the formula.

## How to use

Program needs these parameters to work:

1. Base file path
2. String delimiter, to separate one record (key-value) from another
3. String of this format "keyColumn,valueColumn" (for CSV files)
4. Dictionary file path
5. String delimiter, as explained above but applied to this file
6. String indicating columns as explained above but applied to this file
7. (Optional) Path to the file containing the stop words, which will be eliminated from the descriptions given that they
   don't have a meaning by themselves

The first way of providing these parameters is passing them as program arguments at the start.

Additionally, an example of
a [properties file](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Properties.html) is contained
in _src/it/isd/resources/launchArguments.example.properties_. A copy, named
_launchArguments.properties_, can be made and filled in.

Lastly, if none of the above methods worked, the program will ask the user to submit the required parameters from the
terminal.

## Attributions

- File stopWords.txt is taken from [here](https://github.com/Alir3z4/stop-words)