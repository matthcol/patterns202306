#include <vector>
#include <list>
#include <iostream>
#include <cstdlib>
#include <string>

template <class Iterator>
void printlnIterable(Iterator first, Iterator last, std::string bullet) {
    while (first != last) {
        std::cout << bullet << *first << std::endl;
        ++first;
    }
}

void iterateContainers() {
    std::vector<std::string> cities { "Toulouse", "Pau", "Marseille", "Lyon", "Paris"};
    printlnIterable(cities.cbegin(), cities.cend(), " - ");
    printlnIterable(cities.crbegin(), cities.crend(), " # ");
    printlnIterable(cities.cbegin()+1, cities.cend()-1, " ~ ");
    
    std::list<std::string> cityList(cities.cbegin(), cities.cend());
    printlnIterable(cityList.cbegin(), cityList.cend(), " @ ");

    // parcours par reference constante
    for (const auto& city: cities) {
        std::cout << city << " ";
    }
    std::cout << std::endl;
}


int main (){
    iterateContainers();
    return EXIT_SUCCESS;
}