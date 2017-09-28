#include <fstream>
#include <string>
#include <iostream>
using namespace std;
 
int getHashCode(string s);
 
const int HASHCONST = 9029;
 
template<class T>
class LinkedListNode
{
public:
    LinkedListNode(T value, T key, LinkedListNode<T>& next, LinkedListNode<T>& prev)
    {
        this->key = key;
        this->Value = value;
        this->Next = &next;
        this->Previous = &prev;
    }
 
    LinkedListNode()
    {
        hash = -1;
        key = -1;       
        Next = nullptr;
        Previous = nullptr;
    }
 
    int hash;
    T key;
    T Value;
    LinkedListNode<T>* After;
    LinkedListNode<T>* Before;
    LinkedListNode<T>* Next;
    LinkedListNode<T>* Previous;
};
 
template<class T>
class LinkedList
{
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
        while (iter->key != keyWord && iter != header) {
            iter = iter->Next;
        }
        if (iter->key == keyWord) {
            return iter->Value;
        }
        return "none";
    }
 
    LinkedListNode<T>* getNode(T keyWord)
    {
        LinkedListNode<T>* iter = header->Next;
        while (iter->key != keyWord && iter != header) {
            iter = iter->Next;
        }
        return iter;
    }
 
    LinkedListNode<T>* Add(T keyWord, T val)
    {
        bool flag = true;
        LinkedListNode<T>* iter = header->Next;
        while (iter->key != keyWord && iter != header) {
            iter = iter->Next;
        }
        if (iter->key == keyWord) {
            iter->Value = val;
            flag = false;
        }
        if (flag)
        {
            LinkedListNode<T>* newNode = new LinkedListNode<T>(val, keyWord, *header, *header->Previous);
            newNode->hash = getHashCode(keyWord);
            newNode->Previous->Next = newNode;
            newNode->Next->Previous = newNode;
            size++;
            return newNode;         
        }
        return new LinkedListNode<T>();
    }
 
    bool Contains(T keyWord)
    {
        if (size > 0) {
            LinkedListNode<T>* iter = header->Next;
            while (iter->key != keyWord && iter != header) {
                iter = iter->Next;
            }
            if (iter->key == keyWord) {
                return true;
            }
        }
        return false;
    }
 
    LinkedListNode<T>* Remove(T keyWord)
    {
        if (size > 0) {
            LinkedListNode<T>* iter = header->Next;
            while (iter != header && iter->key != keyWord) {
                iter = iter->Next;
            }
            if (iter->key == keyWord) {
                iter->Previous->Next = iter->Next;
                iter->Next->Previous = iter->Previous;
                iter->Next = NULL;
                iter->Previous = NULL;
                size--;
                return iter;
            }
        }
        return new LinkedListNode<T>();
    }
 
private:
    LinkedListNode<T>* header;
    int size;
};
 
template<class T>
class Map
{
public:
    Map()
    {
        list = new LinkedList<T>();       
    }
 
    virtual LinkedListNode<T>* put(T keyWord, T val)
    {
        return list->Add(keyWord, val);
    }
 
    virtual LinkedListNode<T>* remove(T keyWord)
    {
        return list->Remove(keyWord);
    }
 
    virtual T get(T keyWord)
    {
        return list->get(keyWord);
    }
 
    virtual LinkedListNode<T>* getNode(T keyWord)
    {
        return list->getNode(keyWord);
    }
 
    LinkedList<T>* list;
};
 
template<class T>
class LinkedHashMap: public Map<T>
{
private:
    Map<T>* mapa;
    LinkedListNode<T>* HeaderLinkedMap;
 
public:
    LinkedHashMap()
    {
        mapa = new Map<T>[HASHCONST];
        HeaderLinkedMap = new LinkedListNode<T>();
        HeaderLinkedMap->After = HeaderLinkedMap->Before = HeaderLinkedMap;
    }
 
    LinkedListNode<T>* put(T x, T y)
    {
        LinkedListNode<T>* it = mapa[getHashCode(x)].put(x, y);
        if (it->hash != -1) {
            it->After = HeaderLinkedMap;
            it->Before = HeaderLinkedMap->Before;
            it->Before->After = it;
            it->After->Before = it;
        }
        return it;
    }
 
    LinkedListNode<T>* remove(T x)
    {
        LinkedListNode<T>* it = mapa[getHashCode(x)].remove(x);
        if (it->hash != -1) {
            it->After->Before = it->Before;
            it->Before->After = it->After;
        }
        return it;
    }
 
    T get(T x)
    {
        return mapa[getHashCode(x)].get(x);
    }
 
    T prev(T x)
    {
        LinkedListNode<T>* it = mapa[getHashCode(x)].getNode(x);
        if (it->hash != -1) {
            if (it->Before == HeaderLinkedMap) {
                return "none";
            }
            return it->Before->Value;
        } 
        else {
            return "none";
        }
    }
 
    T next(T x)
    {
        LinkedListNode<T>* it = mapa[getHashCode(x)].getNode(x);
        if (it->hash != -1) {
            if (it->After == HeaderLinkedMap) {
                return "none";
            }
            return it->After->Value;
        }
        else {
            return "none";
        }
    }
};
 
int main()
{
    ios_base::sync_with_stdio(false);
    ifstream reader("linkedmap.in", ios_base::in);
    ofstream writer("linkedmap.out", ios_base::trunc);
    LinkedHashMap<string> map;
    string line;
    string x, y;
    while (!reader.eof()) {
        reader >> line;
        reader >> x;
        if (line.compare("+") != 0)
        {
            if (line.compare("put") == 0) {
                reader >> y;
                map.put(x, y);
            }
            else if (line.compare("delete") == 0) {
                map.remove(x);
            }
            else if (line.compare("get") == 0)
            {
                writer << map.get(x) << endl;
            }
            else if (line.compare("prev") == 0)
            {
                writer << map.prev(x) << endl;
            }
            else
            {
                writer << map.next(x) << endl;
            }
        }
        line = "+";
    }
    reader.close();
    writer.close();
}
 
int getHashCode(string s) {
    int ans = 0;
    for (int i = 0; i < s.length(); i++) {
        ans += 31 * ans + s[i];
        ans %= HASHCONST;
    }
    return ans;
}
