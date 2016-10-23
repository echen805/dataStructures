#include <algorithm>
#include <iostream>
#include <vector>

using std::vector;
using std::cin;
using std::cout;
using std::swap;
using std::pair;
using std::make_pair;

class HeapBuilder
{
private:
  vector< int > data;
  vector< pair< int, int > > swaps;

  void WriteResponse() const
  {
    cout << swaps.size() << "\n";
    for ( size_t i = 0; i < swaps.size(); ++i )
    {
      cout << swaps[ i ].first << " " << swaps[ i ].second << "\n";
    }
  }  

  int Parent( int i )
  {
    if ( i == 0 )
      return 0;
    else
      return ( i - 1 ) / 2;
  }

  void ReadData()
  {
    int n;
    cin >> n;
    data.resize( n );
    for ( int i = 0; i < n; ++i )
      cin >> data[ i ];
  }  

  void SiftDown( int i )
  {
    int size = data.size() - 1;
    int maxindex = i;
    int left = 2 * i + 1;
    if ( left <= size && data[ left ] < data[ maxindex ] )
    {
      maxindex = left;
    }

    int right = 2 * i + 2;
    if ( right <= size && data[ right ] < data[ maxindex ] )
    {
      maxindex = right;
    }

    if ( i != maxindex )
    {
      swaps.push_back( make_pair( i, maxindex ) );
      swap( data[ i ], data[ maxindex ] );
      SiftDown( maxindex );
    }
  }

  void GenerateSwaps()
  {
    swaps.clear();
    int n = data.size();
    int mid = n / 2;
    for ( int i = mid; i >= 0; --i )
    {
      SiftDown( i );
    }
  }

public:
  void Solve()
  {
    ReadData();
    GenerateSwaps();
    WriteResponse();
  }
};

int main()
{
  std::ios_base::sync_with_stdio( false );
  HeapBuilder heap_builder;
  heap_builder.Solve();

  return 0;
}