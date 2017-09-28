#include <fstream>
#include <string>
#include <iostream>
using namespace std;
 
int getHashCode(string s);
 
const int HASHCONST = 10000;
 
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
 
    LinkedListNode() {}
 
    T key;
    T Value;
    LinkedListNode* Next;
    LinkedListNode* Previous;
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
 
    string get(T keyWord)
    {
        LinkedListNode<T>* iter = header->Next;
        string report;
        int count = 0;
        while (iter != header) {
            if (iter->key == keyWord) {
                report.append(" " + iter->Value);
                count++;
            }
            iter = iter->Next;
        }       
        report.insert(0, to_string(count));
        return report;
    }
 
    void Add(T keyWord, T val)
    {
        bool flag = true;
        LinkedListNode<T>* iter = header->Next;
        while (iter != header) {
            if (iter->key == keyWord && iter->Value == val) {             
                flag = false;
            }
            iter = iter->Next;
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
            while (iter->key != keyWord && iter != header) {
                iter = iter->Next;
            }
            if (iter->key == keyWord) {
                return true;
            }
        }
        return false;
    }
 
    void Remove(T keyWord, T val)
    {
        if (size > 0) {
            LinkedListNode<T>* iter = header->Next;
            while (iter != header && (iter->key != keyWord || iter->Value != val)) {
                iter = iter->Next;
            }
            if (iter->key == keyWord && iter->Value == val) {
                iter->Previous->Next = iter->Next;
                iter->Next->Previous = iter->Previous;
                iter->Next = NULL;
                iter->Previous = NULL;
                size--;
            }
        }
    }
 
    void deleteAll(T keyWord)
    {
        if (size > 0) {
            LinkedListNode<T>* iter = header->Next;
            while (iter != header) {
                if (iter->key == keyWord) {
                    iter->Previous->Next = iter->Next;
                    iter->Next->Previous = iter->Previous;                                     
                    size--;
                }
                iter = iter->Next;
                                 
            }           
        }
    }
 
private:
    LinkedListNode<T>* header;
    int size;
};
 
template<class T>
class MultiMap
{
public:
    MultiMap()
    {
        list = new LinkedList<T>();
        key = -1;
    }
 
    void put(T keyWord, T val)
    {
        list->Add(keyWord, val);
    }
 
    void remove(T keyWord, T val)
    {
        list->Remove(keyWord, val);
    }
 
    T get(T keyWord)
    {
        return list->get(keyWord);
    }
 
    void deleteAll(T keyWord)
    {
        list->deleteAll(keyWord);
    }
 
private:
    T key;
    LinkedList<T>* list;
};
 
int main()
{
    ios_base::sync_with_stdio(false);
    ifstream reader("multimap.in", ios_base::in);
    ofstream writer("multimap.out", ios_base::trunc);
    MultiMap<string>* map = new MultiMap<string>[HASHCONST];
    string line;
    string x, y;
    while (!reader.eof()) {
        reader >> line;
        reader >> x;
        if (line.compare("+") != 0)
        {
            if (line.compare("put") == 0) {
                reader >> y;
                map[getHashCode(x)].put(x, y);
            }
            else if (line.compare("delete") == 0) {
                reader >> y;
                map[getHashCode(x)].remove(x, y);
            }
            else if (line.compare("deleteall") == 0)
            {
                map[getHashCode(x)].deleteAll(x);
            }
            else
            {
                writer << map[getHashCode(x)].get(x) << endl;
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
