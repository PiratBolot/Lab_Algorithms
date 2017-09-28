#include <fstream>
#include <string>
#include <iostream>
using namespace std;
 
#define H 100000
 
int getHashCode(string s) {
    int h = 0;
    for (int i = 0; i < s.length(); i++) {
        h += 31 * h + s[i];
        h %= H;
    }
    return h;
}
 
 
template<class T>
class LinkedListNode
{
public:
    T key;
    T Value;
    LinkedListNode* Next;
    LinkedListNode* Previous;
 
    LinkedListNode(T value, T key, LinkedListNode<T>& next, LinkedListNode<T>& prev)
    {
        this->key = key;
        this->Value = value;
        this->Next = &next;
        this->Previous = &prev;
    }
 
    LinkedListNode() {}
};
 
template<class T>
class LinkedList
{
private:
    LinkedListNode<T>* header;
    int size;
public:
    LinkedList()
    {
        header = new LinkedListNode<T>();
        header->Next = header->Previous = header;
        size = 0;
    }
 
    T get(T keyWord)
    {
        LinkedListNode<T>* iter = header->Next;
        while (iter->key != keyWord && iter->Next != header) {
            iter = iter->Next;
        }
        if (iter->key == keyWord) {
            return iter->Value;
        }
        return "none";
    }
 
    void Add(T keyWord, T val)
    {
        bool flag = true;
        LinkedListNode<T>* iter = header->Next;
        while (iter->key != keyWord && iter->Next != header) {
            iter = iter->Next;
        }
        if (iter->key == keyWord) {
            iter->Value = val;
            flag = false;
        }
        if (flag)
        {
            LinkedListNode<T>* newNode = new LinkedListNode<T>(val, keyWord, *header, *header->Previous);
            newNode->Previous->Next = newNode;
            newNode->Next->Previous = newNode;
            size++;
        }
    }
 
    bool Contains(T keyWord)
    {
        if (size > 0) {
            LinkedListNode<T>* iter = header->Next;
            while (iter->key != keyWord && iter->Next != header) {
                iter = iter->Next;
            }
            if (iter->key == keyWord) {
                return true;
            }
        }
        return false;
    }
 
    void Remove(T keyWord)
    {
        if (size > 0) {
            LinkedListNode<T>* iter = header->Next;
            while (iter->Next != header && iter->key != keyWord) {
                iter = iter->Next;
            }
            if (iter->key == keyWord) {
                iter->Previous->Next = iter->Next;
                iter->Next->Previous = iter->Previous;
                iter->Next = NULL;
                iter->Previous = NULL;
                size--;
            }
        }
    }
};
 
template<class T>
class Map
{
private:
    T key;
    LinkedList<T>* list;
public:
    Map()
    {
        list = new LinkedList<T>();
        key = -1;
    }
 
    void put(T keyWord, T val)
    {
        list->Add(keyWord, val);
    }
 
    void remove(T keyWord)
    {
        list->Remove(keyWord);
    }
 
    T get(T keyWord)
    {
        return list->get(keyWord);
    }
};
 
int main() {
 
    ifstream start("map.in");
    ofstream result("map.out");
 
    Map<string>* map = new Map<string>[H];
    string line;
    string x, y;
    while (!start.eof()) {
        start >> line;
        start >> x;
        if (line.compare("+") != 0)
        {
            if (line.compare("put") == 0) {
                start >> y;
                map[getHashCode(x)].put(x, y);
            }
            else if (line.compare("delete") == 0) {
                map[getHashCode(x)].remove(x);
            }
            else
            {
                result << map[getHashCode(x)].get(x) << endl;
            }
        }
        line = "+";
    }
    start.close();
    result.close();
}
