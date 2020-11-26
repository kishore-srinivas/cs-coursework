#include "HitCounter.h"

HitCounter::HitCounter(City* city) {
	this->counter = 0;
	this->city = city;
}

HitCounter::~HitCounter() {}

void HitCounter::incrementCounter() {
	this->counter++;
}

City* HitCounter::getCity() {
	return this->city;
}

int HitCounter::getCounter() {
	return this->counter;
}